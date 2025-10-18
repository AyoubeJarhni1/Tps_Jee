package com.example;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LDAPDeleteContact {
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
            
            String dn = "uid=mcourtois,ou=contacts,ou=system";
            
            ctx.destroySubcontext(dn);
            System.out.println("Entrée supprimée : " + dn);
            
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}