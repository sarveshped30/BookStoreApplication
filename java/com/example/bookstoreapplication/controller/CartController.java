package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.CartDTO;
import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.BookOutOfStockException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.services.ICartService;
import com.example.bookstoreapplication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controls API request regarding cart services and provides http response
 **/
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    //-------------------------Adding book to the cart of user-----------------------//
    @GetMapping("/add/{bookId}/{userId}")
    public ResponseEntity<ResponseDTO> addToCart(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId)throws UserNotFoundException, BookNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("Added book with Id " + bookId + " to cart of user with Id " + userId,
                                                    cartService.addToCart(bookId, userId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------Removing book from cart of user-------------------------//
    @GetMapping("/remove/{bookId}/{userId}")
    public ResponseEntity<ResponseDTO> removeFromCart(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId) throws UserNotFoundException, BookNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("Removed book with Id " + bookId + " from cart of user with Id " + userId,
                                                    cartService.removeFromCart(bookId, userId));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/addQuantity/{bookId}/{userId}")
    public ResponseEntity<Integer> addBookQuantity(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId) throws UserNotFoundException, BookNotFoundException {
        return new ResponseEntity<>(cartService.addBookQuantity(userId,bookId), HttpStatus.OK);
    }

    @GetMapping("/removeQuantity/{bookId}/{userId}")
    public ResponseEntity<Integer> removeBookQuantity(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId) throws UserNotFoundException, BookNotFoundException {
        return new ResponseEntity<>(cartService.removeBookQuantity(userId,bookId), HttpStatus.OK);
    }
}
