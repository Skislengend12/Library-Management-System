package com.example.Student_Library_Management_System.Repositories;

import com.example.Student_Library_Management_System.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Integer> {

    // here we are writing the query as there is no predefined function for the same, and
    // we have to do it for every other function except finding by id which is predefined
    // it will always return the object or the list of objects
    Student findByEmail(String email);
    // abstract method no need to give implementation as the required code will be written by hibernate
    // we can write most of the basic queries of finding in the same manner

    List<Student> findByCountry(String country);
}
