package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller takes API calls for ordering book and provide http response
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    //------------------------------Placing order---------------------------------//
    @PostMapping("")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestParam("bookId") int bookId,
                                                  @RequestParam("userId") int userId) throws UserNotFoundException, BookNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("Order successful", orderService.placeOrder(bookId, userId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
