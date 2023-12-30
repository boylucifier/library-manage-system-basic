package com.digital.library.project.librarymanagesys2dec.services;

import com.digital.library.project.librarymanagesys2dec.exceptionsModel.*;
import com.digital.library.project.librarymanagesys2dec.models.*;
import com.digital.library.project.librarymanagesys2dec.repositery.TransactionRepos;
import com.sun.jdi.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepos transactionRepos;
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;

    @Value("${student.book.limit}")
    int studentBookLimit;
    @Value("${book.return.days}")
    int bookReturnDayLimit;
    @Value("${book.fine.day}")
    int finePerDay;
    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public String issueBook(Integer studentId, Integer bookId) throws StudentNotFoundException, BookNotFoundException,MaxBookLimitReachedException, BookAlreadyAssignedException {

        Student student = studentService.getStudentById(studentId); // will take care of null student
        Book book = bookService.getBookById(bookId); // will take care of null book

        if(student.getBooks().size()>=studentBookLimit){
            throw new MaxBookLimitReachedException("Student has already taken maximum book allowed to take at a time, please return the book in-order to issue the current book");
        }
        if(null != book.getStudent()){
            throw new BookAlreadyAssignedException("Book has already been assigned to other student, Please wait for some time");
        }

        logger.info("Student and book found by mentioned id and book is available");

        //Creating a transaction with pending status
        Transaction transaction = Transaction.builder().transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.PENDING).student(student)
                .book(book).transactionId(UUID.randomUUID()).build();
        transactionRepos.save(transaction);

        try{
            book.setStudent(student);
            bookService.addBook(book); // save method works for saving new entity as well as updating existing entity
            transaction.setTransactionStatus(TransactionStatus.PROCESSED);
            transactionRepos.save(transaction);
            return "Transaction successful, transaction id : "+transaction.getTransactionId().toString();
        }catch(Exception exception){
            //rolling back assignment of book to student
            book.setStudent(null);
            bookService.addBook(book);
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepos.save(transaction);
            return "Transaction unsuccessful, please try again, transaction id : "+ transaction.getTransactionId().toString();
        }

    }

    public String returnBook(Integer studentId, Integer bookId) throws StudentNotFoundException, BookNotFoundException, BookCantReturnException, TransactionNotFoundException {
        Student student = studentService.getStudentById(studentId); // will take care of null student
        Book book = bookService.getBookById(bookId); // will take care of null book

        //business restriction that book can be returned by student who has taken it
        if(null == book.getStudent()){
            throw new BookCantReturnException("This book is not assigned to anyone currently, please provide correct bookId");
        }
        if(studentId != book.getStudent().getStudentId()){
            throw new BookCantReturnException(("This book can't be returned by provided studentId, please provide correct student id"));
        }
        logger.info("book and student found and validation succeeded, return book process starts");
        Transaction transaction = Transaction.builder().transactionId(UUID.randomUUID()).book(book)
                .student(student).transactionType(TransactionType.RETURN).transactionStatus(TransactionStatus.PENDING).build();
        transactionRepos.save(transaction);

//                Optional<Transaction> optionalTransactionRecord = book.getTransaction().stream()
//                                    .sorted((t1, t2)->t2.getCreatedOn().compareTo(t1.getCreatedOn()))
//                                    .filter(t -> t.getTransactionType().toString().equalsIgnoreCase(TransactionType.ISSUE.toString()))
//                                    .filter(t -> t.getTransactionStatus().toString().equalsIgnoreCase(TransactionStatus.PROCESSED.toString()))
//                                    .findFirst();
//
//        if(optionalTransactionRecord.isEmpty()){
//            throw new BookCantReturnException("Not able to find Transaction record for issue of this book, please try after some time");
//        }
//        Transaction transactionRecord = optionalTransactionRecord.get();
//
//        we can also use jpa query to sort this out

        List<Transaction> transactionRecords = transactionRepos.findByStudentAndBookAndTransactionTypeOrderByIdDesc(student,book,TransactionType.ISSUE);
        if(CollectionUtils.isEmpty(transactionRecords)){
            throw new TransactionNotFoundException("Could not found transaction for book issue for mentioned book id and student id");
        }
        Transaction transactionRecord =  transactionRecords.get(0);

        Long issueDate = transactionRecord.getUpdatedOn().getTime();
        Long currentDate = System.currentTimeMillis();
        Long daysPassed = TimeUnit.MILLISECONDS.toDays(currentDate-issueDate);
        long fine = 0;
        if(daysPassed > bookReturnDayLimit){
            fine = finePerDay*(daysPassed-bookReturnDayLimit);
        }
        transaction.setFine((int)fine);

        try{
            book.setStudent(null);
            bookService.addBook(book);

            transaction.setTransactionStatus(TransactionStatus.PROCESSED);
            transactionRepos.save(transaction);
            return "transaction successful , transaction id : "+transaction.getTransactionId().toString();
        }catch(Exception exc){
            book.setStudent(student);
            bookService.addBook(book);
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepos.save(transaction);
            //return "transaction unsuccessful ,please try after some time, transaction id : "+transaction.getTransactionId().toString();
            throw new InternalException("Unexpected error occured while processing your request, please try after some time, transaction id : "+transaction.getTransactionId().toString());
        }

    }
}
