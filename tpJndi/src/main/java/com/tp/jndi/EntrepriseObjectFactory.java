package com.tp.jndi;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * Factory pour recréer l'objet Entreprise à partir de sa référence JNDI
 */
public class EntrepriseObjectFactory implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
            throws Exception {
        if (obj instanceof Reference ref) {
            // Ici, on peut lire les données de la référence si nécessaire
            return new Entreprise(); // retourne un nouvel objet Entreprise
        }
        return null;
    }
}
