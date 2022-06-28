package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.email.EmailSender;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.BookOutOfStockException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.BookStock;
import com.example.bookstoreapplication.model.Order;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.BookStockRepository;
import com.example.bookstoreapplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for placing order
 **/
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookStockRepository bookStockRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private EmailSender emailSender;

    @Override
    public Order placeOrder(int bookId, int userId) throws UserNotFoundException, BookNotFoundException, BookOutOfStockException {
        Book book = bookService.getBookByBookId(bookId);
        User user = userService.getUserById(userId);
        removeFromStock(book,user);
        Order order = Order.Build(orderRepository.findAll().size() + 1, user.getName(),book.getBookName(), 1);
        sendOrderMail(order, user);
        return orderRepository.save(order);
    }

    public void removeFromStock(Book book, User user) throws BookNotFoundException, BookOutOfStockException {
        BookStock bookStock = bookService.getBookStockByBookName(book.getBookName());

        if(user.getBooks().contains(book)) {
            if(bookStock.getQuantity() > 0) {
                bookStock.setQuantity(bookStock.getQuantity() - 1);
                bookStockRepository.save(bookStock);
            } else {
                throw new BookOutOfStockException("Book out of stock");
            }
        } else {
            throw new BookNotFoundException("Book Not added to cart");
        }
    }

    public void sendOrderMail(Order order, User user) {
        emailSender.sendEmail(user.getEmailId(), "Book Order", "Hello " + order.getUserName() + ", you order for "
                            + order.getBookName() + " is placed successfully.\n" + "order Id: " + order.getOrderId() +
                            "\n!!Thanks for using Book Store App!!");
    }
}
