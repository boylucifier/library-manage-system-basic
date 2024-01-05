package com.digital.library.project.librarymanagesys2dec.services;

import com.digital.library.project.librarymanagesys2dec.exceptionsModel.StudentNotFoundException;
import com.digital.library.project.librarymanagesys2dec.models.Student;
import com.digital.library.project.librarymanagesys2dec.repositery.StudentRepos;
import com.digital.library.project.librarymanagesys2dec.security.User;
import com.digital.library.project.librarymanagesys2dec.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepos studentRepos;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Value("${user.authority.student}")
    private String student_authority;

    public void  addStudent(Student student) throws SQLException {
        User user = student.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(student_authority);
        userService.saveUser(user);
        studentRepos.save(student);
    }

    public Student getStudentById(Integer studentId) throws StudentNotFoundException{
        return studentRepos.findById(studentId).orElseThrow(()-> new StudentNotFoundException("student not found by the given id"));
    }

    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok(studentRepos.findAll());
    }
    public Student getStudentByUserName(String userName){
        return studentRepos.getStudentByUserName(userName);
    }
}
