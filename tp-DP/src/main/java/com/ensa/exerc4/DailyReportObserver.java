package com.ensa.exerc4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DailyReportObserver implements StockObserver {

    private static List<String> dailyLog = new ArrayList<>();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void update(Stock stock) {
        String logEntry = String.format("[%s] %s → quantité = %d",
                LocalDateTime.now().format(dtf), stock.getName(), stock.getQuantity());
        dailyLog.add(logEntry);
        System.out.println("RAPPORT JOURNALIER : " + logEntry);
    }

    public static void printDailyReport() {
        System.out.println("\nRAPPORT JOURNALIER COMPLET :");
        System.out.println("=".repeat(50));
        for (String entry : dailyLog) {
            System.out.println(entry);
        }
        System.out.println("=".repeat(50));
    }
}