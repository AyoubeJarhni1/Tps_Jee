package server;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {

    // Namespace WS-Security standard
    private static final String WSSE_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private static final String WSU_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

    // Utilisateurs valides (simulé - étendez avec une DB si besoin)
    private static final Map<String, String> VALID_USERS = new HashMap<>();
    static {
        VALID_USERS.put("clientUser", "clientPassword");
        // Ajoutez-en d'autres : VALID_USERS.put("admin", "secret");
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        // Vérifier si c'est une requête entrante (inbound)
        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (Boolean.TRUE.equals(outbound)) {
            return true; // Ignorer les réponses sortantes
        }

        try {
            SOAPMessage message = context.getMessage();
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
            SOAPHeader header = envelope.getHeader();

            if (header == null) {
                throw createSoapFault(envelope, "wsse:InvalidSecurity", "Missing WS-Security header");
            }

            // Chercher <wsse:Security>
            QName securityQName = new QName(WSSE_NS, "Security");
            Iterator<Node> securityIter = header.getChildElements(securityQName);
            if (!securityIter.hasNext()) {
                throw createSoapFault(envelope, "wsse:InvalidSecurity", "Missing <wsse:Security> header");
            }

            SOAPElement securityHeader = (SOAPElement) securityIter.next();

            // Chercher <wsse:UsernameToken> dans <wsse:Security>
            QName utQName = new QName(WSSE_NS, "UsernameToken");
            Iterator<Node> utIter = securityHeader.getChildElements(utQName);
            if (!utIter.hasNext()) {
                throw createSoapFault(envelope, "wsse:InvalidSecurity", "Missing <wsse:UsernameToken>");
            }

            SOAPElement usernameToken = (SOAPElement) utIter.next();

            // Extraire Username
            String username = getChildText(usernameToken, WSSE_NS, "Username");
            // Extraire Password (et optionnellement son Type)
            String password = getChildText(usernameToken, WSSE_NS, "Password");

            if (username == null || password == null) {
                throw createSoapFault(envelope, "wsse:InvalidSecurity", "Username ou Password manquant dans UsernameToken");
            }

            // Valider credentials
            if (!VALID_USERS.containsKey(username) || !VALID_USERS.get(username).equals(password)) {
                throw createSoapFault(envelope, "wsse:FailedAuthentication", "Authentification échouée : credentials invalides");
            }

            System.out.println("Authentification réussie pour l'utilisateur : " + username);
            return true;

        } catch (SOAPException e) {
            throw new RuntimeException("Erreur SOAP dans handler", e);
        }
    }

    // Méthode utilitaire pour extraire texte d'un enfant
    private String getChildText(SOAPElement parent, String namespace, String localName) {
        QName qname = new QName(namespace, localName);
        Iterator<Node> iter = parent.getChildElements(qname);
        if (iter.hasNext()) {
            return iter.next().getTextContent().trim();
        }
        return null;
    }

    // Créer un SOAP Fault standard WS-Security
    private SOAPFaultException createSoapFault(SOAPEnvelope envelope, String faultCode, String faultString) throws SOAPException {
        SOAPBody body = envelope.getBody();
        SOAPFault fault = body.addFault();
        QName code = new QName(WSSE_NS, faultCode, "wsse");
        fault.setFaultCode(code);
        fault.setFaultString(faultString);
        fault.setFaultActor("SecurityHandler");
        return new SOAPFaultException(fault);
    }

    @Override
    public Set<QName> getHeaders() {
        // Déclarer que ce handler gère le header WS-Security
        return Set.of(new QName(WSSE_NS, "Security"));
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        // Loguer les fautes (optionnel)
        System.err.println("SOAP Fault générée par SecurityHandler");
        return true;
    }

    @Override
    public void close(MessageContext context) {
        // Rien à fermer
    }
}