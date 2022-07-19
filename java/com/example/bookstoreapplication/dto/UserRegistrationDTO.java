package com.example.bookstoreapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Data transfer object for user registration details
 * Generating getters,setters and constructors using lombook
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotNull(message = "UserName should not be null")
    private String name;

    @NotNull(message = "mobile no shouldn't be null")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobileNo entered")
    private String mobileNo;

    @NotBlank
    @Email(message = "Email address entered is not valid")
    private String emailId;

    @NotNull
    private String password;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private int zipCode;

    @NotNull
    private String address;
}
