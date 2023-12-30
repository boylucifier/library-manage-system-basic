package com.digital.library.project.librarymanagesys2dec.exceptionsModel;

public class MaxBookLimitReachedException extends Exception{
    public MaxBookLimitReachedException(String message){
        super(message);
    }
}
