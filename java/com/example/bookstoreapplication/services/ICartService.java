package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.User;

public interface ICartService {

    User addToCart(int userId, int bookId) throws UserNotFoundException, BookNotFoundException;
    User removeFromCart(int userId, int bookId) throws BookNotFoundException, UserNotFoundException;
}
