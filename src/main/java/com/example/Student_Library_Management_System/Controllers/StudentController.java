package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.StudentMobNoReqDto;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){

        return studentService.createStudent(student);
    }

    @GetMapping("/findByEmail")
    public String findStudentByEmail(@RequestParam("email") String email){
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/findAllByCountry")
    public List<Student> findAllByCountry(@RequestParam("country") String country){
        return studentService.findByCountry(country);
    }

    @PutMapping("/updateMobile")
    public String updateMobNo(@RequestBody StudentMobNoReqDto studentMobNoReqDto){
        return studentService.updateMobNo(studentMobNoReqDto);
    }
}
