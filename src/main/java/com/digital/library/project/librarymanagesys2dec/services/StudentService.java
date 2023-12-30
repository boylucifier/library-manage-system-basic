package com.digital.library.project.librarymanagesys2dec.services;

import com.digital.library.project.librarymanagesys2dec.exceptionsModel.StudentNotFoundException;
import com.digital.library.project.librarymanagesys2dec.models.Student;
import com.digital.library.project.librarymanagesys2dec.repositery.StudentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepos studentRepos;
    public void addStudent(Student student) throws SQLException {
        studentRepos.save(student);
    }

    public Student getStudentById(Integer studentId) throws StudentNotFoundException{
        return studentRepos.findById(studentId).orElseThrow(()-> new StudentNotFoundException("student not found by the given id"));
    }

    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok(studentRepos.findAll());
    }
}
