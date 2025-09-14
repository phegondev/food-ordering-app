package com.phegon.FoodApp.exceptions;

public class UnauthorizedAccessException extends RuntimeException{

    public UnauthorizedAccessException(String message){
        super(message);
    }
}
