package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.dto.UserRegistrationDTO;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * UserRegistration controller for taking api calls for user registration and user data management
 **/
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/register")
public class userRegistrationController {

    @Autowired
    private IUserService userService;

    //----------------------------Registering user------------------------------//
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserRegistrationDTO userRegDTO) {
        ResponseDTO responseDTO = ResponseDTO.Build("User registration successful", userService.addUser(userRegDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //---------------------------Viewing all users----------------------------//
    @GetMapping("/view")
    public ResponseEntity<ResponseDTO> getUsers() {
        ResponseDTO responseDTO = ResponseDTO.Build("View all registered users", userService.getUsers());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //-------------------------View user by userId----------------------------//
    @GetMapping("/view/{userId}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable int userId) throws UserNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("View user by id: " + userId, userService.getUserById(userId));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    //----------------------update user by userId----------------------------//
    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO> updateUserById(@PathVariable int userId, @RequestBody @Valid UserRegistrationDTO userRegistrationDTO) throws UserNotFoundException {
        ResponseDTO responseDTO = ResponseDTO.Build("Updated user by id: " + userId, userService.updateUserById(userId,userRegistrationDTO));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    //-----------------------Delete user by userId--------------------------//
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable int userId) throws UserNotFoundException{
        userService.deleteUserById(userId);
        return new ResponseEntity<>("Deleted user by id: " + userId, HttpStatus.OK);
    }
}
