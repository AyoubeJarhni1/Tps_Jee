package client;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.util.Set;

public class ClientSecurityHandler implements SOAPHandler<SOAPMessageContext> {

    private final String username;
    private final String password;

    public ClientSecurityHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (Boolean.TRUE.equals(outbound)) {
            try {
                SOAPMessage message = context.getMessage();
                SOAPPart soapPart = message.getSOAPPart();
                SOAPEnvelope envelope = soapPart.getEnvelope();
                SOAPHeader header = envelope.getHeader();
                if (header == null) {
                    header = envelope.addHeader();
                }

                // Namespaces
                String wsseNs = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
                String wsuNs = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

                // <wsse:Security>
                QName securityQName = new QName(wsseNs, "Security");
                SOAPHeaderElement securityHeader = header.addHeaderElement(securityQName);
                securityHeader.addNamespaceDeclaration("wsse", wsseNs);
                securityHeader.addNamespaceDeclaration("wsu", wsuNs);
                securityHeader.setMustUnderstand(true);

                // <wsse:UsernameToken>
                SOAPElement usernameToken = securityHeader.addChildElement("UsernameToken", "wsse");

                // <wsse:Username>
                SOAPElement usernameEl = usernameToken.addChildElement("Username", "wsse");
                usernameEl.setTextContent(username);

                // <wsse:Password>
                SOAPElement passwordEl = usernameToken.addChildElement("Password", "wsse");
                passwordEl.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
                passwordEl.setTextContent(password);

                message.saveChanges();

                System.out.println("Header WS-Security ajouté : UsernameToken avec " + username);

            } catch (SOAPException e) {
                throw new RuntimeException("Erreur lors de l'ajout du UsernameToken", e);
            }
        }
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return Set.of(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security"));
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.err.println("Erreur SOAP reçue du serveur");
        return true;
    }

    @Override
    public void close(MessageContext context) {}
}