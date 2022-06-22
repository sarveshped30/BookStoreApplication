package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
