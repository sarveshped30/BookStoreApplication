package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.CartDTO;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for cart service
 **/
@Service
public class CartService implements ICartService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IUserService userService;

    @Override
    public User addToCart(int bookId, int userId) throws UserNotFoundException, BookNotFoundException {
        Book book = bookService.getBookByBookId(bookId);
        User user = userService.getUserById(userId);

        user.getBooks().add(book);
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
}
