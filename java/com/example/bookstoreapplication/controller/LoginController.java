package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.LoginDTO;
import com.example.bookstoreapplication.exception.UsernamePasswordInvalidException;
import com.example.bookstoreapplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/** loginController contains methods which takes rest api calls for login
 * where loginDTO object passed as request body by client is authenticated
 * and token is generated for authenticated user
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Authenticate user based on username password and provides token, if fails to authenticate
     * throws Invalid username password exception
     **/
    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody LoginDTO loginDTO) throws UsernamePasswordInvalidException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));
        } catch (Exception ex) {
            throw new UsernamePasswordInvalidException("Invalid username or password");
        }
        return new ResponseEntity<>("Token generated: " + jwtUtil.generateToken(loginDTO.getUserName()), HttpStatus.OK);
    }
}
