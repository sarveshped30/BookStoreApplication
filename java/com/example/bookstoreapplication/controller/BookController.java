package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.BookDTO;
import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controls API requests regarding book services and provides proper http response
 * in form of response body and http status
 **/
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/seller")
public class BookController {

    @Autowired
    private IBookService bookServices;

    //----------------------------Adding Books to stock----------------------------------//
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody @Valid BookDTO bookDTO) {
        ResponseDTO responseDTO = ResponseDTO.Build("Added Book to store", bookServices.addBookStock(bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //---------------------------Viewing all books in stock------------------------------//
    @GetMapping("/view")
    public ResponseEntity<ResponseDTO> getBooks() {
        ResponseDTO responseDTO = ResponseDTO.Build("View all the Books in store", bookServices.getBooks());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //------------------------Getting quantity of book by book name----------------------//
    @GetMapping("/getQuantity/{bookName}")
    public ResponseEntity<Integer> getBookQuantity(@PathVariable String bookName) throws BookNotFoundException {
        return new ResponseEntity<>(bookServices.getBookQuantity(bookName), HttpStatus.OK);
    }

    //-----------------------------Viewing book by book id-------------------------------//
    @GetMapping("/view/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable int bookId) throws BookNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("View book with id: " + bookId, bookServices.getBookByBookId(bookId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------------Updating book by book id-------------------------------//
    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookById(@PathVariable int bookId, @RequestBody @Valid BookDTO bookDTO) throws BookNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("Updated book with id: " + bookDTO, bookServices.updateBookById(bookId,bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //---------------------------Deleting book by book id-------------------------------//
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable int bookId) throws BookNotFoundException {
        bookServices.deleteBookById(bookId);
        return new ResponseEntity<>("deleted book with id: " + bookId, HttpStatus.OK);
    }

    @GetMapping("/sort/asc")
    public ResponseEntity<ResponseDTO> sortAscending() {
        ResponseDTO responseDTO = ResponseDTO.Build("Sorted in ascending order", bookServices.sortBooksAscending());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/sort/desc")
    public ResponseEntity<ResponseDTO> sortDescending() {
        ResponseDTO responseDTO = ResponseDTO.Build("Sorted in descending order", bookServices.sortBooksDescending());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/initiate/{bookId}")
    public ResponseEntity<ResponseDTO> initiateBookQuantity(@PathVariable int bookId) throws BookNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("Initiated..", bookServices.initiateBookQuantity(bookId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/initiateAll")
    public ResponseEntity<ResponseDTO> initiateAllBooksQuantity() {
        ResponseDTO responseDTO = ResponseDTO.Build("Initiated all books..", bookServices.initiateAllBooksQuantity());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
