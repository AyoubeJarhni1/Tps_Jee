package com.ensa.exerc5;

public class Report extends Document {
    private String department;
    private String date;

    public Report(String title, String author, String content, String department, String date) {
        super(title, author, content);
        this.department = department;
        this.date = date;
    }

    @Override
    public void printInfo() {
        System.out.println("RAPPORT : " + title);
        System.out.println("  Auteur : " + author + " | Département : " + department);
        System.out.println("  Date : " + date);
        System.out.println("  Contenu : " + content.substring(0, Math.min(50, content.length())) + "...");
    }

    @Override
    public Document clone() {
return new Report(this.title, this.author, this.content, this.department, this.date);    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}