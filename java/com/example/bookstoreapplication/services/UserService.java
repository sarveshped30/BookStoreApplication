package com.example.bookstoreapplication.services;

import com.example.bookstoreapplication.dto.UserRegistrationDTO;
import com.example.bookstoreapplication.email.EmailSender;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for performing user CURD operations
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    @Override
    public User addUser(UserRegistrationDTO userRegDTO) {
        User user = User.Build(userRepository.findAll().size() + 1, userRegDTO.getName(), userRegDTO.getMobileNo(),
                    userRegDTO.getEmailId(), userRegDTO.getPassword(),userRegDTO.getCity(), userRegDTO.getState(),
                    userRegDTO.getZipCode(), userRegDTO.getAddress(), null);
        sendRegistrationMail(user);
        return userRepository.save(user);
    }

    public void sendRegistrationMail(User user) {
        emailSender.sendEmail(user.getEmailId(), "Registration",
                              "Congratulations!!, you have successfully registered to book store app," +
                                      " Your registration Id is: " + user.getUserId());
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) throws UserNotFoundException {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        return user;
    }

    @Override
    public User updateUserById(int userId, UserRegistrationDTO userRegistrationDTO) throws UserNotFoundException {
        User user = getUserById(userId);
        user.setName(userRegistrationDTO.getName());
        user.setMobileNo(userRegistrationDTO.getMobileNo());
        user.setEmailId(userRegistrationDTO.getEmailId());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setAddress(userRegistrationDTO.getAddress());
        user.setZipCode(userRegistrationDTO.getZipCode());
        user.setCity(userRegistrationDTO.getCity());
        user.setState(userRegistrationDTO.getState());
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(int userId) throws UserNotFoundException {
        User user = getUserById(userId);
        if(user != null) {
            userRepository.delete(user);
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }

    @Override
    public int getUserIdByUserName(String userName) {
        int userId = userRepository.findByName(userName).getUserId();
        return userId;
    }

    @Override
    public int userBookCounts(int userId) throws UserNotFoundException {
        return this.getUserById(userId).getBooks().size();
    }

}
