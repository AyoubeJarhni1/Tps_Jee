package com.tp.jndiproject;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/testJNDI")
public class TestJndiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Début HTML
        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>Test JNDI - Catalogue Produits</title>");
        out.println("    <style>");
        out.println("        * { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; ");
        out.println("               background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
        out.println("               min-height: 100vh; padding: 40px 20px; }");
        out.println("        .container { max-width: 900px; margin: 0 auto; ");
        out.println("                     background: white; border-radius: 15px; ");
        out.println("                     box-shadow: 0 20px 60px rgba(0,0,0,0.3); ");
        out.println("                     overflow: hidden; }");
        out.println("        .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
        out.println("                  color: white; padding: 30px; text-align: center; }");
        out.println("        .header h1 { font-size: 2em; margin-bottom: 10px; }");
        out.println("        .header p { opacity: 0.9; font-size: 1.1em; }");
        out.println("        .content { padding: 30px; }");
        out.println("        .success { background: #d4edda; border-left: 4px solid #28a745; ");
        out.println("                   padding: 15px; margin-bottom: 20px; border-radius: 4px; }");
        out.println("        .success-icon { color: #28a745; font-weight: bold; font-size: 1.2em; }");
        out.println("        .error { background: #f8d7da; border-left: 4px solid #dc3545; ");
        out.println("                 padding: 15px; margin-bottom: 20px; border-radius: 4px; }");
        out.println("        .error-icon { color: #dc3545; font-weight: bold; font-size: 1.2em; }");
        out.println("        table { width: 100%; border-collapse: collapse; margin-top: 20px; ");
        out.println("                box-shadow: 0 2px 8px rgba(0,0,0,0.1); }");
        out.println("        thead { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }");
        out.println("        th { color: white; padding: 15px; text-align: left; ");
        out.println("             font-weight: 600; text-transform: uppercase; font-size: 0.9em; }");
        out.println("        td { padding: 12px 15px; border-bottom: 1px solid #e0e0e0; }");
        out.println("        tbody tr:hover { background: #f8f9fa; transition: background 0.3s; }");
        out.println("        tbody tr:last-child td { border-bottom: none; }");
        out.println("        .stats { display: flex; justify-content: space-around; ");
        out.println("                 margin-top: 30px; padding: 20px; ");
        out.println("                 background: #f8f9fa; border-radius: 8px; }");
        out.println("        .stat-box { text-align: center; }");
        out.println("        .stat-number { font-size: 2em; font-weight: bold; ");
        out.println("                       color: #667eea; margin-bottom: 5px; }");
        out.println("        .stat-label { color: #6c757d; font-size: 0.9em; }");
        out.println("        .badge { background: #667eea; color: white; padding: 4px 12px; ");
        out.println("                 border-radius: 12px; font-size: 0.85em; font-weight: 600; }");
        out.println("        .price { color: #28a745; font-weight: 600; font-size: 1.1em; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='header'>");
        out.println("            <h1>🛍️ Catalogue des Produits</h1>");
        out.println("            <p>Test de connexion JNDI avec DataSource</p>");
        out.println("        </div>");
        out.println("        <div class='content'>");

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Étape 1 : Contexte JNDI
            Context ctx = new InitialContext();
            out.println("            <div class='success'>");
            out.println("                <span class='success-icon'>✓</span> ");
            out.println("                Contexte JNDI initialisé");
            out.println("            </div>");

            // Étape 2 : Lookup DataSource
            DataSource ds = (DataSource) ctx.lookup("java:/dsCatalogue");
            out.println("            <div class='success'>");
            out.println("                <span class='success-icon'>✓</span> ");
            out.println("                DataSource récupéré : <span class='badge'>java:/dsCatalogue</span>");
            out.println("            </div>");

            // Étape 3 : Connexion
            conn = ds.getConnection();
            out.println("            <div class='success'>");
            out.println("                <span class='success-icon'>✓</span> ");
            out.println("                Connexion établie à la base : <strong>" + conn.getCatalog() + "</strong>");
            out.println("            </div>");

            // Étape 4 : Requête
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM produit ORDER BY id");

            // Étape 5 : Affichage
            out.println("            <table>");
            out.println("                <thead>");
            out.println("                    <tr>");
            out.println("                        <th>ID</th>");
            out.println("                        <th>Nom du Produit</th>");
            out.println("                        <th>Prix (DH)</th>");
            out.println("                    </tr>");
            out.println("                </thead>");
            out.println("                <tbody>");

            int count = 0;
            double totalPrix = 0;

            while (rs.next()) {
                count++;
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                totalPrix += prix;

                out.println("                    <tr>");
                out.println("                        <td><strong>#" + id + "</strong></td>");
                out.println("                        <td>" + nom + "</td>");
                out.println("                        <td class='price'>" + String.format("%.2f", prix) + " DH</td>");
                out.println("                    </tr>");
            }

            out.println("                </tbody>");
            out.println("            </table>");

            // Statistiques
            out.println("            <div class='stats'>");
            out.println("                <div class='stat-box'>");
            out.println("                    <div class='stat-number'>" + count + "</div>");
            out.println("                    <div class='stat-label'>Produits</div>");
            out.println("                </div>");
            out.println("                <div class='stat-box'>");
            out.println("                    <div class='stat-number'>" + String.format("%.2f", totalPrix) + " DH</div>");
            out.println("                    <div class='stat-label'>Valeur Totale</div>");
            out.println("                </div>");
            if (count > 0) {
                out.println("                <div class='stat-box'>");
                out.println("                    <div class='stat-number'>" + String.format("%.2f", totalPrix/count) + " DH</div>");
                out.println("                    <div class='stat-label'>Prix Moyen</div>");
                out.println("                </div>");
            }
            out.println("            </div>");

        } catch (Exception e) {
            out.println("            <div class='error'>");
            out.println("                <span class='error-icon'>✗</span> ");
            out.println("                <strong>Erreur :</strong> " + e.getMessage());
            out.println("            </div>");
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}