package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.UserRegistrationDTO;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.User;

import java.util.List;

public interface IUserService {

    User addUser(UserRegistrationDTO userRegDTO);
    List<User> getUsers();
    //String login(LoginDTO loginDTO);
    User getUserById(int userId) throws UserNotFoundException;
    User updateUserById(int userId, UserRegistrationDTO userRegistrationDTO) throws UserNotFoundException;
    void deleteUserById(int userId) throws UserNotFoundException;
   // User addToCart(String bookName, String userName, int quantity) throws BookOutOfStockException;
}