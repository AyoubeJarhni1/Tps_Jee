package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class RMIClient_GestionLivres {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            LivreService service = (LivreService) registry.lookup("MyLivreService");

            Scanner sc = new Scanner(System.in);
            int choix;

            do {
                System.out.println("\n=== MENU BIBLIOTHÈQUE ===");
                System.out.println("1. Ajouter un livre");
                System.out.println("2. Rechercher un livre");
                System.out.println("3. Lister tous les livres");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                choix = sc.nextInt();
                sc.nextLine();

                switch (choix) {
                    case 1:
                        System.out.print("Titre : ");
                        String titre = sc.nextLine();
                        System.out.print("Auteur : ");
                        String auteur = sc.nextLine();
                        System.out.print("Année de publication : ");
                        int annee = sc.nextInt();

                        Livre nouveauLivre = new Livre(titre, auteur, annee);
                        String message = service.ajouterLivre(nouveauLivre);
                        System.out.println(message);
                        break;

                    case 2:
                        System.out.print("Titre du livre à rechercher : ");
                        String titreRecherche = sc.nextLine();
                        Livre livre = service.rechercherLivre(titreRecherche);
                        if (livre != null) {
                            System.out.println("Livre trouvé : " + livre.getTitre() + " - " +
                                    livre.getAuteur() + " (" + livre.getAnneePublication() + ")");
                        } else {
                            System.out.println(" Livre introuvable.");
                        }
                        break;

                    case 3:
                        List<Livre> livres = service.listerLivres();
                        if (livres.isEmpty()) {
                            System.out.println("Aucun livre enregistré.");
                        } else {
                            System.out.println("Liste des livres :");
                            for (Livre l : livres) {
                                System.out.println("- " + l.getTitre() + " | " + l.getAuteur() +
                                        " | " + l.getAnneePublication());
                            }
                        }
                        break;

                    case 0:
                        System.out.println("Au revoir !");
                        break;

                    default:
                        System.out.println("Choix invalide.");
                }
            } while (choix != 0);

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
