package com.ensa.tuto5.model;


public class Book {

    private Long id ;

    public Book(String title, Long id, String author, String isbn, int year) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    private String title , author , isbn ;
   private int year ;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }
}
