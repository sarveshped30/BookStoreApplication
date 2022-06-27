package com.example.bookstoreapplication.exception;

public class BookOutOfStockException extends Exception{

    public BookOutOfStockException(String message) {
        super(message);
    }
}
