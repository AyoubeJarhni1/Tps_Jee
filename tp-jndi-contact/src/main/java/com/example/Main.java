package com.example;

public class Main {
    public static void main(String[] args) {
        try {
            JNDIContactService service = new JNDIContactService();

            // operation d'ajout de contacts
            Contact contact1 = new Contact("Fatih", "fatih@example.com", "987654321");
            Contact contact2 = new Contact("Ichmawin", "ichmawin@example.com", "123456789");
            Contact contact3 = new Contact("Jarhni", "jarhni@example.com", "123456789");

            service.ajouterContact("Fatih", contact1);
            service.ajouterContact("Ichmawin", contact2);
            service.ajouterContact("Jarhni", contact3);

            // recherche de contacts
            Contact contactRecherche = service.rechercherContact("Fatih");
            System.out.println("Contact trouvé: " + contactRecherche);

            service.fermer();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Une erreur s'est produite.");
        }
    }
}