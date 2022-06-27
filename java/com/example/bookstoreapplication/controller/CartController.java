package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.BookOutOfStockException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.services.ICartService;
import com.example.bookstoreapplication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controls API request regarding cart services and provides http response
 **/
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    //-------------------------Adding book to the cart of user-----------------------//
    @PutMapping("/add")
    public ResponseEntity<ResponseDTO> addToCart(@RequestParam("userId") int userId,
                                                 @RequestParam("bookId") int bookId) throws UserNotFoundException, BookNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("Added book with Id " + bookId + " to cart of user with Id " + userId,
                                                    cartService.addToCart(userId, bookId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------Removing book from cart of user-------------------------//
    @PutMapping("/remove")
    public ResponseEntity<ResponseDTO> removeFromCart(@RequestParam("userId") int userId,
                                                      @RequestParam("bookId") int bookId) throws UserNotFoundException, BookNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("Removed book with Id " + bookId + " from cart of user with Id " + userId,
                                                    cartService.removeFromCart(userId,bookId));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
