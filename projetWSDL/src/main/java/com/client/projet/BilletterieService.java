package com.client.projet;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class BilletterieService {
    @WebMethod
    public String rechercherVol(String destination) {
        switch (destination.toLowerCase()) {
            case "paris":
                return "Vol trouvé : AF123 - Paris - 14:30";
            case "londres":
                return "Vol trouvé : BA789 - Londres - 09:45";
            case "new york":
                return "Vol trouvé : UA456 - New York - 11:20";
            case "tokyo":
                return "Vol trouvé : JL321 - Tokyo - 16:55";
            default:
                return "Aucun vol trouvé pour : " + destination;
        }
    }


}