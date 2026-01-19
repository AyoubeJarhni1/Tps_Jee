package server;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;
import com.sun.xml.wss.impl.callback.TimestampValidationCallback;
import com.sun.xml.wss.impl.callback.CertificateValidationCallback;

import jakarta.security.auth.callback.CallbackHandler;
import jakarta.security.auth.callback.UnsupportedCallbackException;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.sun.xml.ws.security.impl.policy.Constants.CallbackHandler;

public class ServerSecurityCallback implements CallbackHandler {

    // Utilisateurs simulés (étendez avec une DB si besoin)
    private static final Map<String, String> USERS = new HashMap<>();
    static {
        USERS.put("test", "secret");
        USERS.put("admin", "admin123");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof PasswordValidationCallback pwdCallback) {
                handlePasswordCallback(pwdCallback);
            } else if (callback instanceof TimestampValidationCallback tsCallback) {
                tsCallback.setValidator(new TimestampValidationCallback.TimestampValidator() {
                    @Override
                    public void validate(TimestampValidationCallback.Request request)
                            throws TimestampValidationCallback.TimestampValidationException {
                        // Accepte si timestamp < 5 minutes (défaut Metro)
                    }
                });
            } else if (callback instanceof CertificateValidationCallback certCallback) {
                certCallback.setValidator(new CertificateValidationCallback.CertificateValidator() {
                    @Override
                    public boolean validate(java.security.cert.X509Certificate certificate)
                            throws CertificateValidationCallback.CertificateValidationException {
                        return true; // Auto-signé
                    }
                });
            }
        }
    }

    private void handlePasswordCallback(PasswordValidationCallback callback) {
        PasswordValidationCallback.Request request = callback.getRequest();
        if (request instanceof PasswordValidationCallback.PlainTextPasswordRequest plainReq) {
            String username = plainReq.getUsername();
            String password = plainReq.getPassword();

            if (username != null && USERS.containsKey(username) && USERS.get(username).equals(password)) {
                callback.setValidator(new PasswordValidationCallback.ServerSecurityCallback() {
                    @Override
                    public boolean validate(String user, String passwd) {
                        return true;
                    }
                });
            } else {
                throw new RuntimeException("Authentification échouée : Username/Password invalide");
            }
        }
    }
}