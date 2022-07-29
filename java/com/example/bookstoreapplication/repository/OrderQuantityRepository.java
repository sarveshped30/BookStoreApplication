package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.OrderQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderQuantityRepository extends JpaRepository<OrderQuantity, Integer> {

}
