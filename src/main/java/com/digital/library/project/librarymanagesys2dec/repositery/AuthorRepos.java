package com.digital.library.project.librarymanagesys2dec.repositery;

import com.digital.library.project.librarymanagesys2dec.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepos extends JpaRepository<Author,Integer> {
    @Query(value="Select * from Author where email= :email",nativeQuery = true)
    Author getAuthorByEmail(String email);
}
