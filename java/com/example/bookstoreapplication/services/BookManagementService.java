package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.BookDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.BookStock;
import com.example.bookstoreapplication.repository.BookStockRepository;
import com.example.bookstoreapplication.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service class Contains business logic for performing CURD operations for books
 **/
@Service
public class BookManagementService implements IBookService{

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private BookStockRepository bookStockRepository;

    @Override
    public Book addBookStock(BookDTO bookDTO) {
        Book book = addBook(bookDTO);
        BookStock bookStock = BookStock.Build(bookDTO.getBookName(), bookDTO.getQuantity());
        bookStockRepository.save(bookStock);
        return book;
    }

    public Book addBook(BookDTO bookDTO) {
        Book book = Book.Build(bookRepository.findAll().size()+1,bookDTO.getBookName(), bookDTO.getAuthor(), bookDTO.getBookPrize());
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public int getBookQuantity(String bookName) throws BookNotFoundException {
        int quantity =  bookStockRepository.findByBookName(bookName).getQuantity();
        if(quantity == 0) {
            throw new BookNotFoundException("Book not found with name: " + bookName);
        }
        return quantity;
    }

    @Override
    public Book getBookByBookId(int bookId) throws BookNotFoundException {
        Book book =  bookRepository.findByBookId(bookId);
        if(book == null) {
            throw new BookNotFoundException("Book not found with Id: " + bookId);
        }
        return book;
    }

    @Override
    public Book updateBookById(int bookId, BookDTO bookDTO) throws BookNotFoundException {
        Book book = getBookByBookId(bookId);
        updateBookStock(book.getBookName(), bookDTO);
        book.setBookName(bookDTO.getBookName());
        book.setAuthor(bookDTO.getAuthor());
        book.setBookPrize(bookDTO.getBookPrize());
        return bookRepository.save(book);
    }

    public void updateBookStock(String bookName, BookDTO bookDTO) throws BookNotFoundException {
        BookStock bookStock = getBookStockByBookName(bookName);
        bookStock.setBookName(bookDTO.getBookName());
        bookStock.setQuantity(bookDTO.getQuantity());
        bookStockRepository.save(bookStock);
    }

    @Override
    public BookStock getBookStockByBookName(String bookName) throws BookNotFoundException {
        BookStock bookStock = bookStockRepository.findByBookName(bookName);
        if(bookStock == null) {
            throw new BookNotFoundException("Book not found with name: " + bookName);
        }
        return bookStock;
    }

    @Override
    public void deleteBookById(int bookId) throws BookNotFoundException {
        Book book = getBookByBookId(bookId);
        deleteBookStockByName(book.getBookName());
        bookRepository.delete(book);
    }

    private void deleteBookStockByName(String bookName) throws BookNotFoundException {
        bookStockRepository.delete(getBookStockByBookName(bookName));
    }
}
