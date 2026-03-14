package com.ensa.exerc5;

public class Main {
    public static void main(String[] args) {
System.out.println("TEST : CONTRACT - FACTORY + PROTOTYPE\n");
System.out.println("=" .repeat(60));

DocumentFactory factory = new DocumentFactory();

System.out.println("1. CRÉATION VIA FACTORY");
Document contract1 = factory.createDocument("contract");
contract1.printInfo();
System.out.println();

System.out.println("2. CLONAGE DU CONTRAT");
Document contract2 = contract1.clone();

((Contract) contract2).setTitle("Contrat Cloné 2026");
((Contract) contract2).setAmount(25000.0);
((Contract) contract2).sign();

System.out.println("CLONE MODIFIÉ :");
contract2.printInfo();
System.out.println();

System.out.println("3. VÉRIFICATION : L'ORIGINAL N'A PAS CHANGÉ");
System.out.println("ORIGINAL :");
contract1.printInfo();

System.out.println("\nPreuve d'indépendance :");
System.out.println("   → Titre original  : '" + contract1.getTitle() + "'");
System.out.println("   → Titre clone     : '" + ((Contract) contract2).getTitle() + "'");
System.out.println("   → Montant original: " + ((Contract) contract1).getAmount() + " DH");
System.out.println("   → Montant clone   : " + ((Contract) contract2).getAmount() + " DH");
System.out.println("   → Signé original  : " + ((Contract) contract1).isSigned());
System.out.println("   → Signé clone     : " + ((Contract) contract2).isSigned());

System.out.println("\n" + "=".repeat(60));
System.out.println("TEST CONTRACT RÉUSSI !");
    
}
}