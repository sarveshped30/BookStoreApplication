package com.example.bookstoreapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores user info
 **/
@Entity
@Table(name = "UserData")
@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class User {

    @Id
    private int userId;
    private String name;
    private String mobileNo;
    private String emailId;
    private String password;
    private String city;
    private String state;
    private int zipCode;
    private String address;
    @ManyToMany
    private List<Book> books = new ArrayList<>();

}
