package com.tp.jndiproject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class TestJndi {
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("    TEST JNDI - DataSource Local");
        System.out.println("========================================\n");

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Étape 1 : Créer le contexte JNDI initial
            System.out.println("➤ Création du contexte JNDI...");
            Context ctx = new InitialContext();
            System.out.println("✓ Contexte créé avec succès\n");

            // Étape 2 : Lookup du DataSource
            System.out.println("➤ Recherche du DataSource : java:/dsCatalogue");
            DataSource ds = (DataSource) ctx.lookup("java:/dsCatalogue");
            System.out.println("✓ DataSource trouvé\n");

            // Étape 3 : Obtenir une connexion
            System.out.println("➤ Connexion à la base de données...");
            conn = ds.getConnection();
            System.out.println("✓ Connexion établie");
            System.out.println("  Base : " + conn.getCatalog());
            System.out.println("  Metadata : " + conn.getMetaData().getDatabaseProductName() + "\n");

            // Étape 4 : Exécuter la requête
            System.out.println("➤ Exécution de la requête : SELECT * FROM produit\n");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM produit");

            // Étape 5 : Afficher les résultats
            System.out.println("┌──────┬────────────────────────────┬──────────┐");
            System.out.printf("│ %-4s │ %-26s │ %-8s │%n", "ID", "Nom", "Prix");
            System.out.println("├──────┼────────────────────────────┼──────────┤");

            int count = 0;
            while (rs.next()) {
                count++;
                System.out.printf("│ %-4d │ %-26s │ %8.2f │%n",
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix")
                );
            }

            System.out.println("└──────┴────────────────────────────┴──────────┘");
            System.out.println("\nNombre total de produits : " + count);
            System.out.println("\n✓ Test réussi !\n");

        } catch (Exception e) {
            System.err.println("\n✗ ERREUR : " + e.getMessage());
            System.err.println("\nDétails de l'erreur :");
            e.printStackTrace();

        } finally {
            // Étape 6 : Fermer les ressources
            System.out.println("\n➤ Fermeture des ressources...");
            try {
                if (rs != null) {
                    rs.close();
                    System.out.println("✓ ResultSet fermé");
                }
                if (st != null) {
                    st.close();
                    System.out.println("✓ Statement fermé");
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("✓ Connexion fermée");
                }
            } catch (SQLException e) {
                System.err.println("✗ Erreur lors de la fermeture : " + e.getMessage());
            }

            System.out.println("\n========================================");
            System.out.println("           FIN DU TEST");
            System.out.println("========================================");
        }
    }
}
