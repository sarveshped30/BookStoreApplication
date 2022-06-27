package com.example.bookstoreapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object for login details
 * Generating getters,setters and constructors using lombook
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String userName;
    private String emailId;
    private String password;
}
