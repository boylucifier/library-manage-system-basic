package com.digital.library.project.librarymanagesys2dec.repositery;

import com.digital.library.project.librarymanagesys2dec.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepos extends JpaRepository<Book,Integer> {
}
