package com.digital.library.project.librarymanagesys2dec.controllers;

import com.digital.library.project.librarymanagesys2dec.createRequest.StudentCreateRequest;
import com.digital.library.project.librarymanagesys2dec.exceptionsModel.StudentNotFoundException;
import com.digital.library.project.librarymanagesys2dec.models.Student;
import com.digital.library.project.librarymanagesys2dec.services.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value="/createStudent", method = RequestMethod.POST)
    public void createStudent(@Valid @RequestBody StudentCreateRequest student) throws SQLException {
        studentService.addStudent(student.to());
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) throws StudentNotFoundException {
        log.info("inside controller");
        return studentService.getStudentById(studentId);
    }
    @GetMapping("/getDetails")
    public Student getStudentDetail(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return studentService.getStudentByUserName(user.getUsername());
    }

    @RequestMapping(value="/getAllStudent",method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudent(){
        return studentService.getAllStudent();
    }


}
