package com.digital.library.project.librarymanagesys2dec.controllers;

import com.digital.library.project.librarymanagesys2dec.exceptionsModel.*;
import com.digital.library.project.librarymanagesys2dec.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issueBook")
    public String issueBook(@RequestParam("studentId") Integer studentId, @RequestParam("bookId") Integer bookId) throws BookNotFoundException, StudentNotFoundException, MaxBookLimitReachedException, BookAlreadyAssignedException {
        return transactionService.issueBook(studentId,bookId);
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("studentId") Integer studentId, @RequestParam("bookId") Integer bookId) throws BookCantReturnException, StudentNotFoundException, BookNotFoundException, TransactionNotFoundException {
        return transactionService.returnBook(studentId,bookId);
    }

}
