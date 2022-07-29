package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.CartDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.User;

public interface ICartService {

    User addToCart(int bookId, int userId) throws UserNotFoundException, BookNotFoundException;
    User removeFromCart(int bookId, int userId) throws BookNotFoundException, UserNotFoundException;
    int addBookQuantity(int userId, int bookId) throws UserNotFoundException, BookNotFoundException;

    int removeBookQuantity(int userId, int bookId) throws UserNotFoundException, BookNotFoundException;

    long getTotalPrize(int userId) throws UserNotFoundException;
    User emptyCart(int userId) throws UserNotFoundException;
}
