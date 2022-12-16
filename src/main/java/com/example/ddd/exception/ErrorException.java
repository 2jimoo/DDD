package com.example.ddd.exception;

public class ErrorException extends RuntimeException{
    public ErrorException(Error error){
        super(error.message());
    }
}
