package com.tp.jndi;

import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entreprise implements Serializable, Referenceable {
    private static final long serialVersionUID = 1L;
    private List<Employe> employes;
    public Entreprise() {
        this.employes = new ArrayList<>();
    }
    public void ajouterEmploye(Employe e) {
        if (e != null) {
            if (rechercherEmploye(e.getMatricule()) != null) {
                System.out.println("Un employé avec le matricule " + e.getMatricule() + " existe déjà.");
                return;
            }
            employes.add(e);
            System.out.println("Employé ajouté avec succès : " + e);
        }
    }
    public Employe rechercherEmploye(String matricule) {
        for (Employe emp : employes) {
            if (emp.getMatricule().equals(matricule)) {
                return emp;
            }
        }
        return null;
    }
    public List<Employe> listerTousLesEmployes() {
        return new ArrayList<>(employes);
    }
    public int getNombreEmployes() {
        return employes.size();
    }
    @Override
    public Reference getReference() {
        return new Reference(
                Entreprise.class.getName(),
                new StringRefAddr("info", "Entreprise placeholder"),
                EntrepriseObjectFactory.class.getName(),
                null
        );
    }
}
