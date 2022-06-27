package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.Order;

public interface IOrderService {

    Order placeOrder(int bookId, int userId) throws UserNotFoundException, BookNotFoundException;
}
