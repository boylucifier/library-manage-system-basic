package com.digital.library.project.librarymanagesys2dec.handlingException;

import com.digital.library.project.librarymanagesys2dec.exceptionsModel.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationException {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidSituation(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(),error.getDefaultMessage()));
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AuthorNotFoundException.class)
    public Map<String,String> handleAuthorNotFoundException(AuthorNotFoundException ex){
        Map<String,String> errorMap= new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = StudentNotFoundException.class)
    public Map<String,String> handleStudentNotFoundException(StudentNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= BookNotFoundException.class)
    public Map<String,String> handleBookNotFoundException(BookNotFoundException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(value= MaxBookLimitReachedException.class)
    public Map<String,String> handleMaxBookLimitException(MaxBookLimitReachedException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(value= BookAlreadyAssignedException.class)
    public Map<String,String> handleBookAlreadyAssignedException(BookAlreadyAssignedException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(value= BookCantReturnException.class)
    public Map<String,String> handleBookCantReturnException(BookCantReturnException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value= TransactionNotFoundException.class)
    public Map<String,String> handleTransactionNotFoundException(TransactionNotFoundException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }
}