package com.example.Bookstore.domain;

public class Book {

    String title;
    String author;
    Integer year;
    String isbn;
    Float price;

    public Book(String title, String author, Integer year, String isbn, Float price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.price = price;
    }
    
}
