package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.OrderDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.BookOutOfStockException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.Order;

public interface IOrderService {

    Order placeOrder(int userId,long totalPrize, OrderDTO orderDTO) throws UserNotFoundException, BookNotFoundException, BookOutOfStockException;

    int latestOrderId();
}
