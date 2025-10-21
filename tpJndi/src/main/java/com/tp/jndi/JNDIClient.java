package com.tp.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class JNDIClient {

    public static void main(String[] args) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            env.put(Context.PROVIDER_URL, "file:/C:/JNDI");
            Context ctx = new InitialContext(env);
            Entreprise entreprise = (Entreprise) ctx.lookup("entreprise");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Ajouter un employé");
                System.out.println("2. Rechercher un employé");
                System.out.println("3. Lister tous les employés");
                System.out.println("4. Quitter");
                System.out.print("Choix : ");
                int choix = sc.nextInt();
                sc.nextLine();
                switch (choix) {
                    case 1 -> {
                        System.out.print("Matricule : ");
                        String m = sc.nextLine();
                        System.out.print("Nom : ");
                        String n = sc.nextLine();
                        System.out.print("Poste : ");
                        String p = sc.nextLine();
                        entreprise.ajouterEmploye(new Employe(m, n, p));
                        System.out.println("✅ Employé ajouté !");
                    }
                    case 2 -> {
                        System.out.print("Matricule à rechercher : ");
                        String m = sc.nextLine();
                        Employe e = entreprise.rechercherEmploye(m);
                        if (e != null) System.out.println(e);
                        else System.out.println("❌ Employé non trouvé !");
                    }
                    case 3 -> {
                        List<Employe> liste = entreprise.listerTousLesEmployes();
                        if (liste.isEmpty()) System.out.println("Aucun employé trouvé.");
                        else liste.forEach(System.out::println);
                    }
                    case 4 -> {
                        ctx.close();
                        System.out.println("Fermeture du client JNDI.");
                        return;
                    }
                    default -> System.out.println("Choix invalide !");
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
