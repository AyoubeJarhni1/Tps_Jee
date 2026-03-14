package com.client.projet;

import jakarta.xml.ws.Endpoint;

public class Serveur {

    public static void main(String[] args) {
        String url = "http://localhost:8080/billetterie";
        Endpoint.publish(url, new BilletterieService());
        System.out.println("Service web démarré : " + url);
        System.out.println("WSDL disponible à : " + url + "?wsdl");
    }
}
