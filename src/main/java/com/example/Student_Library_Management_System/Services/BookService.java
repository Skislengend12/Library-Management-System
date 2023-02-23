package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookReqDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookReqDto bookReqDto) {

        int authorId = bookReqDto.getAuthorId(); // id parameter pass by the postman
        Book book = new Book();

        // using try catch block due to if there is any exception occurs at sql connection
        // or if there is author is not present then also it will throw an exception
        // no need to save book separately as it will be saved along with author due to cascading effect
        // in Spring Boot is a phenomenon that occurs when a change to a single component or configuration
        // option affects several other components and configurations. This can have both positive and negative effects on an application
        try {
            book.setName(bookReqDto.getName());
            book.setPages(bookReqDto.getPages());
            book.setGenre(bookReqDto.getGenre());
            book.setIssueStatus(false);

            Author author = authorRepository.findById(authorId).get(); // finding the author by the id passed by the user from postman
            book.setAuthor(author); // setting the attributes
            author.getBooksWritten().add(book);
            // adding the book to the list of books written by author it is not possible to view it in database directly,
            // but it will be stored in the database by using references from both the table
            authorRepository.save(author); // updating the author
        } catch (Exception e) {
            //System.out.println("Author does not exist.");
            return "Author does not exist";
        }
        return "Book added successfully";
    }
}
