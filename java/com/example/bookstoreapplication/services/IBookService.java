package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.BookDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.BookStock;

import java.util.List;

public interface IBookService {

    Book addBookStock(BookDTO bookDTO);
    List<Book> getBooks();
    int getBookQuantity(String bookName) throws BookNotFoundException;
    BookStock getBookStockByBookName(String bookName) throws BookNotFoundException;
    Book getBookByBookId(int bookId) throws BookNotFoundException;
    Book updateBookById(int bookId, BookDTO bookDTO) throws BookNotFoundException;
    void deleteBookById(int bookId) throws BookNotFoundException;
}
