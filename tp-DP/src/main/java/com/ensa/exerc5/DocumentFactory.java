package com.ensa.exerc5;

public class DocumentFactory {

    public Document createDocument(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type de document ne peut pas être null ou vide");
        }

        String normalizedType = type.trim().toLowerCase();

        return switch (normalizedType) {
            case "report" -> new Report(
                "Nouveau Rapport", 
                "Auteur Inconnu", 
                "Contenu du rapport...", 
                "Département", 
                "2025-11-16"
            );
            case "contract" -> new Contract(
                "Nouveau Contrat", 
                "Auteur Inconnu", 
                "Conditions générales...", 
                "Client", 
                0.0
            );
        
            default -> throw new IllegalArgumentException("Type inconnu : " + type 
                + " (utilisez 'report', 'contract')");
        };
    }
}