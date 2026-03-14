import javax.xml.ws.BindingProvider;
import java.util.Map;

public class SecureClient {
    public static void main(String[] args) {
        HelloService service = new HelloServiceService().getHelloServicePort();

        Map<String, Object> reqContext = ((BindingProvider) service).getRequestContext();

        reqContext.put("ws-security.username", "user1");
        reqContext.put("ws-security.password", "pass1");
        System.setProperty("javax.net.ssl.trustStore", "client-truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        String response = service.sayHello("Ayoub");
        System.out.println("Réponse sécurisée : " + response);
    }
}

