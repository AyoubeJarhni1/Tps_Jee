package com.tp.jndi;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class TestJNDI {
    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/dsCatalogue");

            try (Connection conn = ds.getConnection()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM produit");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " - " +
                            rs.getString("nom") + " - " +
                            rs.getDouble("prix"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

