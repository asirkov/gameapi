package com.checkers.gameapi.service;

import com.checkers.gameapi.dto.UsrDto;
import com.checkers.gameapi.repositories.UsrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UsrRepository usrRepository;

//    @Awtowired
//    SessionRepository sessionRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    public boolean checkAuth(UsrDto user) {
//        final UsrDto checkingUser = usrRepository.getUserDtoByUsername(user.getUsername());
//        PasswordEncoder passwordEncoder = passwordEncoder();
//
//        if (passwordEncoder.encode(user.getPassword()+user.getPasswordSalt())
//                .equals(checkingUser.getPassword()+checkingUser.getPasswordSalt())) {
//            return true;
//        }
//    }

}
