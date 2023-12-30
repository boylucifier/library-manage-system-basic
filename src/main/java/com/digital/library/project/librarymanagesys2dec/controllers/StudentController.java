package com.digital.library.project.librarymanagesys2dec.controllers;

import com.digital.library.project.librarymanagesys2dec.createRequest.StudentCreateRequest;
import com.digital.library.project.librarymanagesys2dec.exceptionsModel.StudentNotFoundException;
import com.digital.library.project.librarymanagesys2dec.models.Student;
import com.digital.library.project.librarymanagesys2dec.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value="/createStudent", method = RequestMethod.POST)
    public void createStudent(@Valid @RequestBody StudentCreateRequest student) throws SQLException {
        studentService.addStudent(student.to());
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) throws StudentNotFoundException {
        return studentService.getStudentById(studentId);
    }

    @RequestMapping(value="/getAllStudent",method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudent(){
        return studentService.getAllStudent();
    }


}
