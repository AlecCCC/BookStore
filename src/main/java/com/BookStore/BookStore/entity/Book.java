package com.BookStore.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name="bookstore")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "author")
    private String authorName;

    @Column(name = "title")
    private String bookTitle;

    @Column(name = "price")
    private double bookPrice;

    @Column(name = "qty")
    private int bookQty;

    public Book() {
    }

    public Book(String authorName, String bookTitle, double bookPrice, int bookQty) {
        this.authorName = authorName;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.bookQty = bookQty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookQty() {
        return bookQty;
    }

    public void setBookQty(int bookQty) {
        this.bookQty = bookQty;
    }
}