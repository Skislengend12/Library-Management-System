package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.StudentMobNoReqDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String createStudent(Student student){

        // to create card along with student first we have to set the attributes of the card class
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student); // we have to tell that which of the attribute for which foreign key has to be stored
        // card will be auto generated when create student is called

        // now we have to set the attributes for the student class also
        student.setCard(card);
        // if there was unidirectional mapping then we have to save both of them
        // but when we are working with bidirectional we have to store the parent and child will be stored automatically
        // by cascading effect, child will be automatically saved

        studentRepository.save(student);
        return "Student added successfully";
    }

    public String getStudentByEmail(String email){
        Student student = studentRepository.findByEmail(email);
        return student.getName();
    }

    public List<Student> findByCountry(String country){
        List<Student> students = studentRepository.findByCountry(country);
        return students;
    }

    public String updateMobNo(StudentMobNoReqDto studentMobNoReqDto){

        // first we will retrieve the data present in our database which belongs to the given id
        // if we directly store this data then other attributes will become null or empty
        Student originalStudent = studentRepository.findById(studentMobNoReqDto.getId()).get();

        // we will keep the properties as it is and only changing the required parameters which we wanted to be changed
        originalStudent.setMobNo(studentMobNoReqDto.getMobNo());

        // and again we will update the old data with new updated data
        studentRepository.save(originalStudent);

        return "Mobile number updated successfully";
    }
}
