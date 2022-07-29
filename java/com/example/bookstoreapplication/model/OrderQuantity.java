package com.example.bookstoreapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class OrderQuantity {

    @Id
    private int srNo;
    private int orderId;
    private String bookName;
    private int bookQuantity;
}
