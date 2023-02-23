package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int pages;

    private boolean issueStatus;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    // many books can have same author
    // step one is make the column of the foreign key
    // book is child class of the author class : author --> book
    @ManyToOne
    @JoinColumn
    private Author author; // parent entity with whom this class is connected

    // one card can have many books
    // we have to connect the card --> book and the connection between them is many-to-one and one-to-many N
    @ManyToOne
    @JoinColumn
    private Card card;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transactions> listOfTransactions = new ArrayList<>();

    public Book() {
    }

    public Book(int id, String name, int pages, Genre genre) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(boolean issueStatus) {
        this.issueStatus = issueStatus;
    }

    public List<Transactions> getListOfTransactions() {
        return listOfTransactions;
    }

    public void setListOfTransactions(List<Transactions> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }
}
