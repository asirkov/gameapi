package com.checkers.gameapi.controllers;

import com.checkers.gameapi.entities.UserDto;
import com.checkers.gameapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserDto> getUsersList() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable("id") Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t get user with id=" + userId));
    }

    @PostMapping("/users")
    public UserDto postUser(@Valid @RequestBody UserDto userDto) {
        return userRepository.save(userDto);
    }

    @PutMapping("/users/{id}")
    public UserDto putUser(@PathVariable("id") Long userId,
                           @Valid @RequestBody UserDto newUserDto) throws Exception {
        UserDto oldUserDto = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t put user=" + newUserDto.toString() +
                                                " to id=" + userId));

        oldUserDto.setId(newUserDto.getId());
        oldUserDto.setLogin(newUserDto.getLogin());
//        oldUser.setPasswordHash(newUser.getPasswordHash());
//        oldUser.setPasswordSalt(newUser.getPasswordSalt());

        return userRepository.save(oldUserDto);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name="id", required=true) Long userId) throws Exception {
        UserDto oldUserDto = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t delete user with id=" + userId));

        userRepository.delete(oldUserDto);
        return ResponseEntity.ok().build();
    }
}
