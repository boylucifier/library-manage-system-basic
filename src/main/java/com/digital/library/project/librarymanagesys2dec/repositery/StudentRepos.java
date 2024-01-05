package com.digital.library.project.librarymanagesys2dec.repositery;

import com.digital.library.project.librarymanagesys2dec.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepos extends JpaRepository<Student,Integer> {

    Student getStudentByUserName(String userName);
}
