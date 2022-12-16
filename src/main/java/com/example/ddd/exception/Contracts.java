package com.example.ddd.exception;

public class Contracts {

    public static void requires(boolean precondition, Error error) {
        if (!precondition) {
            throw new ContractException(error);
        }
    }

    public static void ensures(boolean postcondition, Error error) {
        if (!postcondition) {
            throw new ContractException(error);
        }
    }
}