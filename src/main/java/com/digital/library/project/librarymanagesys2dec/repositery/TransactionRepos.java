package com.digital.library.project.librarymanagesys2dec.repositery;

import com.digital.library.project.librarymanagesys2dec.models.Book;
import com.digital.library.project.librarymanagesys2dec.models.Student;
import com.digital.library.project.librarymanagesys2dec.models.Transaction;
import com.digital.library.project.librarymanagesys2dec.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.digital.library.project.librarymanagesys2dec.models.TransactionType.ISSUE;

@Repository
public interface TransactionRepos extends JpaRepository<Transaction,Integer> {
    //find latest transaction with student , book and transaction status = issue
    List<Transaction> findByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType transactionType);
}
