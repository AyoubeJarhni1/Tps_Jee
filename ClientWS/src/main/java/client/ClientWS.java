package client;

import proxy.BibliothequeWS;
import proxy.BibliothequeWS_Service;
import proxy.Livre;

import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.Binding;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;

public class ClientWS {
    public static void main(String[] args) {
        // Créer le proxy
        BibliothequeWS_Service service = new BibliothequeWS_Service();
        BibliothequeWS proxy = service.getBibliothequeWSPort();

        // === AJOUT DE LA SÉCURITÉ : UsernameToken ===
        BindingProvider bp = (BindingProvider) proxy;
        Binding binding = bp.getBinding();
        @SuppressWarnings("rawtypes")
        List<Handler> handlerChain = binding.getHandlerChain();
        if (handlerChain == null) {
            handlerChain = new ArrayList<>();
        }
        handlerChain.add(new ClientSecurityHandler("clientUser", "clientPassword"));
        binding.setHandlerChain(handlerChain);
        // ===========================================

        // Créer un livre
        Livre livre = new Livre();
        livre.setIsbn("978-0134685991");
        livre.setTitre("Effective Java");
        livre.setAuteur("Joshua Bloch");

        try {
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(new Date());
            XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            livre.setDatePublication(xgc);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        // Appels
        boolean result = proxy.ajouterNouveauLivre(livre);
        System.out.println("AjouterNouveauLivre returned: " + result);

        System.out.println("___________________________________________");
        List<Livre> livres = proxy.listerLivres();
        for (Livre lv : livres) {
            System.out.println("Titre : " + lv.getTitre());
            System.out.println("Auteur: " + lv.getAuteur());
            System.out.println("ISBN  : " + lv.getIsbn());
            XMLGregorianCalendar date = lv.getDatePublication();
            System.out.println("Date de Publication: " + (date != null ? date.toGregorianCalendar().getTime() : "N/A"));
            System.out.println("-------------------------------------------");
        }
    }
}