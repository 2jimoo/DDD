package com.example.ddd.exception;

public class ContractException extends RuntimeException {
    public ContractException(String message) {
        super(message);
    }
}
