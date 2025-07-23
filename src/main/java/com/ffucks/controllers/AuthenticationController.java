package com.ffucks.controllers;

import com.ffucks.dtos.AuthenticationDTO;
import com.ffucks.dtos.RegisterDTO;
import com.ffucks.entities.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ffucks.repositories.UserRepository;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authentication) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authentication.login(), authentication.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO register) {
        if (null != this.repository.findByLogin(register.login())) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        Users users = new Users(register.login(), encryptedPassword, register.role());
        this.repository.save(users);

        return ResponseEntity.ok().build();
    }

}
