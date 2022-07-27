package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Integer> {

    Book findByBookName(String bookName);

    Book findByBookId(int bookId);

    @Query(value = "select * from book ORDER BY book_prize ASC", nativeQuery = true)
    List<Book> sortBooksAscendingOrder();

    @Query(value = "select * from book ORDER BY book_prize DESC", nativeQuery = true)
    List<Book> sortBooksDescendingOrder();
}
