package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.StudentLibraryManagementSystemApplication;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generated
    private int id;

    @CreationTimestamp // auto timestamp the time when the entry is done
    private Date createdOn;

    @UpdateTimestamp // sets time when an entry is updated1
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING) // we have to set manually
    private CardStatus cardStatus;

    // this is written for uni-directional mapping and always written in child class
    // to make it bidirectional we have to write something in parent class also
    // here we want bidirectional relation as card is allocated to the student so in card table student id will be the foreign key
    // and the student also have the card number, so in the student table card number will be the foreign key
    @OneToOne
    @JoinColumn
    private Student student; // this variable is written in  the parent class

    // card is parent of the book class
    // one card can have many students
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Book> booksIssued;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transactions> listOfTransactions = new ArrayList<>();

    public Card() {
        booksIssued = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public List<Transactions> getListOfTransactions() {
        return listOfTransactions;
    }

    public void setListOfTransactions(List<Transactions> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }
}
