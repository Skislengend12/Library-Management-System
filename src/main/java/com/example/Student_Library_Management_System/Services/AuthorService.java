package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AuthorEntryDto authorEntryDto){

        // in the parameters the object is of type dto but the repo interacts only with entities
        // now we have to convert dto to entity

        // created the object
        Author author = new Author();

        // setting its so that we can save correct values in db
        author.setAge(authorEntryDto.getAge());
        author.setName(authorEntryDto.getName());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        authorRepository.save(author);

        return "New author added successfully";
    }
}
