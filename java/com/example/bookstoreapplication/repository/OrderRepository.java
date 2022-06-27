package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
