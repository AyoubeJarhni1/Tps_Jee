package com.ensa.exerc5;

public abstract class Document implements Cloneable {
    protected String title;
    protected String author;
    protected String content;

    
    public Document(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public abstract void printInfo();

    @Override
   public abstract Document clone();

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [title='" + title + "', author='" + author + "']";
    }
}