package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.OrderDTO;
import com.example.bookstoreapplication.email.EmailSender;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.exception.BookOutOfStockException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.*;
import com.example.bookstoreapplication.repository.BookStockRepository;
import com.example.bookstoreapplication.repository.OrderQuantityRepository;
import com.example.bookstoreapplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for placing order
 **/
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderQuantityRepository orderQuantityRepository;

    @Autowired
    private BookStockRepository bookStockRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private EmailSender emailSender;

    @Override
    public Order placeOrder(int userId, long totalPrize, OrderDTO orderDTO) throws UserNotFoundException, BookNotFoundException, BookOutOfStockException {
        User user = userService.getUserById(userId);
        removeFromStock(user);
        Order order = Order.Build((int) ((Math.random() * 8999) + 1000), userId,orderDTO.getName(),
                orderDTO.getMobileNo(), orderDTO.getEmailId(), orderDTO.getAddress(), orderDTO.getCity(),
                orderDTO.getState(), totalPrize,user.getBooks());
        List<Book> books = user.getBooks();
        for (Book book : books) {
            OrderQuantity orderQuantity = OrderQuantity.Build(orderQuantityRepository.findAll().size() + 1,order.getOrderId(), book.getBookName(), book.getQuantity());
            orderQuantityRepository.save(orderQuantity);
        }
        sendOrderMail(order, user);
        return orderRepository.save(order);
    }

    @Override
    public int latestOrderId() {
        List<Order> orders = orderRepository.findAll();
        Order order = orders.get(orders.size() - 1);
        return order.getOrderId();
    }

    public void removeFromStock(User user) throws BookNotFoundException, BookOutOfStockException {
        List<Book> books = user.getBooks();

        if(books != null) {
            for (Book book : books) {
                BookStock bookStock =  bookStockRepository.findByBookName(book.getBookName());
                if(bookStock.getQuantity() > 0) {
                    bookStock.setQuantity(bookStock.getQuantity() - book.getQuantity());
                    bookStockRepository.save(bookStock);
                } else {
                    throw new BookOutOfStockException("Book out of stock");
                }
            }
        }
    }

    public void sendOrderMail(Order order, User user) {
        emailSender.sendEmail(user.getEmailId(), "Book Order", "Hello " + order.getUserName() + ", you order for "
                            + " is placed successfully.\n" + "order Id: " + order.getOrderId() +
                            "\n!!Thanks for using Book Store App!!");
    }
}
