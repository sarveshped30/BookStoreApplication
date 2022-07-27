package com.example.bookstoreapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Book entity contains book info
 **/
@Entity
@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class Book {

    @Id
    private int bookId;
    private String bookName;
    private String author;
    private int bookPrize;
    private int quantity;
    @Lob
    private String bookImage;
}
