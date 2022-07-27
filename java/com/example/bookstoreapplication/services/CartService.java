package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.CartDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.IBookRepository;
import com.example.bookstoreapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for cart service
 **/
@Service
public class CartService implements ICartService{

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IUserService userService;

    @Override
    public User addToCart(int bookId, int userId) throws UserNotFoundException, BookNotFoundException {
        Book bookById = bookService.getBookByBookId(bookId);
        User user = userService.getUserById(userId);

        List<Book> books = user.getBooks();
        if(books != null) {
            if(books.contains(bookById)) {
                bookById.setQuantity(bookById.getQuantity() + 1);
                bookRepository.save(bookById);
            } else {
                user.getBooks().add(bookById);
            }
        } else  {
            user.getBooks().add(bookById);
        }
        return userRepository.save(user);
    }

    @Override
    public User removeFromCart(int bookId, int userId) throws BookNotFoundException, UserNotFoundException {
        Book book = bookService.getBookByBookId(bookId);
        User user = userService.getUserById(userId);

        if(user.getBooks().contains(book)) {
            user.getBooks().remove(book);
        } else {
            throw new BookNotFoundException("User dont have this book in cart");
        }
        return userRepository.save(user);
    }

    @Override
    public int addBookQuantity(int userId, int bookId) throws UserNotFoundException, BookNotFoundException {
        int bookQuantity = 0;
        User user = userService.getUserById(userId);
        Book bookById = bookService.getBookByBookId(bookId);
        List<Book> books =  user.getBooks();
        for (Book book : books) {
            if(bookById.getBookId() == book.getBookId()) {
                book.setQuantity(book.getQuantity() + 1);
                bookRepository.save(book);
                bookQuantity = book.getQuantity();
            }
        }
        return bookQuantity;
    }

    @Override
    public int removeBookQuantity(int userId, int bookId) throws UserNotFoundException, BookNotFoundException {
        int bookQuantity = 0;
        User user = userService.getUserById(userId);
        Book bookById = bookService.getBookByBookId(bookId);
        List<Book> books =  user.getBooks();
        for (Book book : books) {
            if(bookById.getBookId() == book.getBookId()) {
                book.setQuantity(book.getQuantity() - 1);
                bookRepository.save(book);
                bookQuantity = book.getQuantity();
            }
        }
        return bookQuantity;
    }
}
