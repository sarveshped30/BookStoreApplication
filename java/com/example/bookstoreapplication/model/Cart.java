package com.example.bookstoreapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private String name;
    private String mobileNo;
    private String emailId;
    private List<Book> books;
}
