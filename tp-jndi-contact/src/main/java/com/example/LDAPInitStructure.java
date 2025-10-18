package com.example;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LDAPInitStructure {
    public static void main(String[] args) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");
        
        try {
            DirContext ctx = new InitialDirContext(env);
            System.out.println("Connexion LDAP réussie.");
            
            // Créer ou=contacts sous ou=system (qui existe déjà)
            createOrganizationalUnit(ctx, "ou=contacts,ou=system", "contacts");
            
            System.out.println("\n Structure LDAP initialisée avec succès!");
            System.out.println("Vous pouvez maintenant utiliser les autres classes LDAP.");
            
            ctx.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation de la structure LDAP:");
            e.printStackTrace();
        }
    }
    
    private static void createOrganizationalUnit(DirContext ctx, String dn, String ouValue) {
        try {
            Attributes attrs = new BasicAttributes(true);
            Attribute objClass = new BasicAttribute("objectClass");
            objClass.add("top");
            objClass.add("organizationalUnit");
            attrs.put(objClass);
            attrs.put("ou", ouValue);
            
            ctx.createSubcontext(dn, attrs);
            System.out.println("✓ Créé: " + dn);
        } catch (javax.naming.NameAlreadyBoundException e) {
            System.out.println("⚠ Existe déjà: " + dn);
        } catch (NamingException e) {
            System.err.println("✗ Erreur création " + dn + ": " + e.getMessage());
        }
    }
}
