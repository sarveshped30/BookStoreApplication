package com.example.bookstoreapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String userName;
    private String bookName;
    private int quantity;
}
