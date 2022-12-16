package com.example.ddd.exception;

public class ContractException extends ErrorException {
    public ContractException(Error error) {
        super(error);
    }
}