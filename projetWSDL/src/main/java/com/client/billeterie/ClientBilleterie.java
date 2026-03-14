package com.client.billeterie;

import com.ensa.client.BilletterieService;
import com.ensa.client.BilletterieServiceService;

public class ClientBilleterie {
    public static void main(String[] args) {
        try {
            BilletterieServiceService serviceFactory = new BilletterieServiceService();

            BilletterieService port = serviceFactory.getBilletterieServicePort();

            String[] destinations = {"Paris", "Londres", "New York", "Tokyo"};

            for (String destination : destinations) {
                String resultat = port.rechercherVol(destination);
                System.out.println("Vol vers " + destination + " : " + resultat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
