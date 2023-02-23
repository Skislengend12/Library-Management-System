package com.example.Student_Library_Management_System.DTOs;

import com.example.Student_Library_Management_System.Enums.TransactionStatus;

public class IssueBookRequestDto {

    private int bookId;

    private int cardId;


    public IssueBookRequestDto() {
    }

    public IssueBookRequestDto(int bookId, int cardId) {
        this.bookId = bookId;
        this.cardId = cardId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

}
