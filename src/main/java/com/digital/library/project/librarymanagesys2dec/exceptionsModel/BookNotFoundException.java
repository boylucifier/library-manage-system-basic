package com.digital.library.project.librarymanagesys2dec.exceptionsModel;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message){
        super(message);
    }
}