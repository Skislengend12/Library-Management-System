package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    // here we have to issue the book from book repo and add new transaction and add it to the card also.
    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{

        // getting the inputs received from the postman for book and card
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        Book book;
        Card card;
        // retrieving the book and card from the repositories and
        // there is chances of 2 possible exceptions one is sql exception and other NoSuchElement found exception this will be handled by try catch block
        try{
            book = bookRepository.findById(bookId).get();
            card = cardRepository.findById(cardId).get();
        }catch (Exception e){
            throw new Exception("Book or Card not found");
        }
        // making new transaction
        Transactions transactions = new Transactions();

        // setting all the attributes of an transaction entity
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        // if book is not available in the database then exception will be thrown
        // and transaction will mark as failed transaction
        if(book == null || book.isIssueStatus() == true) {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book is not available");
        }

        // if the card is not present in the database or card is not activated or card is blocked then
        // also the transaction will mark as failed and exception will be thrown
        if (card == null || !card.getCardStatus().equals(CardStatus.ACTIVATED)) {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card id is not valid to continue the transaction");
        }

        // if it passes all the checks then the transaction will be marked as successful transaction
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIssueStatus(true);

        // setting all the bidirectional relational attributes
        book.getListOfTransactions().add(transactions);
        card.getListOfTransactions().add(transactions);
        card.getBooksIssued().add(book);

        // we will save the card only and all other entities(book and card) will be saved by the cascading effect
        cardRepository.save(card);

        return "Transaction successful";
    }

    public String getTransaction(int bookId, int cardId) throws Exception{

        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId, cardId);
        if(transactionsList.size() == 0) throw new Exception("No such transactions available");
        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;
    }
}
