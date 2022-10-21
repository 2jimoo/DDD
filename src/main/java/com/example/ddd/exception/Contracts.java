package com.example.ddd.exception;

public class Contracts {

    public static void requires(boolean precondition, String message) {
        if (!precondition) {
            throw new ContractException(message);
        }
    }

    public static void ensures(boolean postcondition, String message) {
        if (!postcondition) {
            throw new ContractException(message);
        }
    }
}
