package com.example;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

public class JNDIContactService {
    private Context ctx;

    public JNDIContactService() throws NamingException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL, "file:/C:/JNDI");
        ctx = new InitialContext(env);
    }

    public void ajouterContact(String nom, Contact contact) throws NamingException {
        Reference ref = new Reference(Contact.class.getName());
        ref.add(new StringRefAddr("nom", contact.getNom()));
        ref.add(new StringRefAddr("email", contact.getEmail()));
        ref.add(new StringRefAddr("telephone", contact.getTelephone()));
        ctx.bind(nom, ref);
    }

    public Contact rechercherContact(String nom) throws NamingException {
        Reference ref = (Reference) ctx.lookup("contacts/" + nom);
        String nomContact = ((StringRefAddr) ref.get("nom")).getContent().toString();
        String email = ((StringRefAddr) ref.get("email")).getContent().toString();
        String telephone = ((StringRefAddr) ref.get("telephone")).getContent().toString();
        return new Contact(nomContact, email, telephone);
    }

    public void fermer() throws NamingException {
        ctx.close();
    }
}
