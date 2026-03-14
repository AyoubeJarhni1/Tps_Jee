package com.ensa.exerc4;

public class UIObserver implements StockObserver {
    @Override
    public void update(Stock stock) {
        System.out.println("Mise à jour de l'interface utilisateur: Le stock de '" + stock.getName() + "' est maintenant de " + stock.getQuantity() + ".");
        updateUI(stock);
    }

    private void updateUI (Stock stock) {
        System.out.println("L'interface utilisateur a été mise à jour pour refléter le nouveau stock de '" + stock.getName() + "'.");
    }   

}
