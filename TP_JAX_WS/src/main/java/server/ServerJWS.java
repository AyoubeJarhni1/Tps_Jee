package server;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.handler.Handler;
import ws.BibliothequeWS;
import java.util.ArrayList;
import java.util.List;

public class ServerJWS {
    public static void main(String[] args) {
        String url = "http://localhost:8080/BibliothequeWS";

        // Instancie le service (important : new BibliothequeWS())
        BibliothequeWS impl = new BibliothequeWS();
        // Créer l'endpoint
        Endpoint endpoint = Endpoint.create(impl);
        // === AJOUT DU HANDLER DE SÉCURITÉ ===
        Binding binding = endpoint.getBinding();
        @SuppressWarnings("rawtypes")
        List<Handler> handlerChain = binding.getHandlerChain();
        if (handlerChain == null) {
            handlerChain = new ArrayList<>();
        }
        handlerChain.add(new SecurityHandler());
        binding.setHandlerChain(handlerChain);

        endpoint.publish(url);

        System.out.println("Web Service publié avec WS-Security (UsernameToken obligatoire) à : " + url + "?wsdl");
        System.out.println("Utilisateurs valides : clientUser / clientPassword");
    }
}