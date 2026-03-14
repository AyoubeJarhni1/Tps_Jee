package com.ensa.exerc4;

public class EmailAlertObserver implements StockObserver {

    

    @Override
    public void update(Stock stock) {
        if (stock.getQuantity() < 5) {
            System.out.println("Alerte par email pourLe stock de " + stock.getName() + "' est bas (" + stock.getQuantity() + " restant).");
            sendEmail(stock);
        }
    }
     
    private void sendEmail(Stock stock) {
        System.out.println("Envoi de l'email  concernant le stock de '" + stock.getName() + "'.");
    }

}
