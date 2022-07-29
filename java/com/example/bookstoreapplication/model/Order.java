package com.example.bookstoreapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores order info
 **/
@Entity
@Table(name = "orderHistory")
@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class Order {

    @Id
    private int orderId;
    private int userId;
    private String userName;
    private String mobileNo;
    private String emailId;
    private String address;
    private String city;
    private String state;
    private long totalPrize;
    @ManyToMany
    private List<Book> books = new ArrayList<>();
}
