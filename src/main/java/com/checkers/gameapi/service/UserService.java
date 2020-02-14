package com.checkers.gameapi.service;

import com.checkers.gameapi.entities.UserDto;
import com.checkers.gameapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

//    @Awtowired
//    SessionRepository sessionRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private String getAuthToken

    public String checkAuth(UserDto user) {
        final UserDto checkingUser = userRepository.getUserDtoByUsername(user.getUsername());
        PasswordEncoder passwordEncoder = passwordEncoder();

        if (passwordEncoder.encode(user.getPassword()+user.getPasswordSalt())
                .equals(checkingUser.getPassword()+checkingUser.getPasswordSalt())) {
            return
        }
    }

}
