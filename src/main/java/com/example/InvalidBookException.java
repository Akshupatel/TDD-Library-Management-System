//handle exception

package com.example;

public class InvalidBookException extends Exception {
    public InvalidBookException(String message) {
        super(message);
    }
}