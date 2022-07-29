package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.OrderDTO;
import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.BookOutOfStockException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * controller takes API calls for ordering book and provide http response
 **/
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    //------------------------------Placing order---------------------------------//
    @PostMapping("/{userId}/{totalPrize}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable("userId") int userId,@PathVariable("totalPrize") long totalPrize,@RequestBody OrderDTO orderDTO) throws UserNotFoundException, BookNotFoundException, BookOutOfStockException {
        ResponseDTO responseDTO = ResponseDTO.Build("Order successful", orderService.placeOrder(userId, totalPrize,orderDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity<Integer> getOrderId() {
        return new ResponseEntity<>(orderService.latestOrderId(), HttpStatus.OK);
    }
}
