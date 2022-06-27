package com.example.bookstoreapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * BookStock entity contains info of stock of books
 **/
@Entity
@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class BookStock {

    @Id
    private String bookName;
    private int quantity;
}
