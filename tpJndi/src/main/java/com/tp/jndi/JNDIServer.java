package com.tp.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JNDIServer {

    public static void main(String[] args) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            env.put(Context.PROVIDER_URL, "file:/C:/JNDI");
            Context ctx = new InitialContext(env);
            Entreprise entreprise = new Entreprise();
            ctx.rebind("entreprise", entreprise);
            System.out.println("✅ Serveur JNDI prêt. Objet 'entreprise' lié avec succès.");
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
