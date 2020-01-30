package com.checkers.gameapi.controllers;

import com.checkers.gameapi.entities.User;
import com.checkers.gameapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t get user with id=" + userId));
    }

    @PostMapping("/users")
    public User postUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User putUser(@PathVariable("id") Long userId,
                        @Valid @RequestBody User newUser) throws Exception {
        User oldUser = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t put user=" + newUser.toString() +
                                                " to id=" + userId));

        oldUser.setId(newUser.getId());
        oldUser.setLogin(newUser.getLogin());
        oldUser.setPasswordHash(newUser.getPasswordHash());
        oldUser.setPasswordSalt(newUser.getPasswordSalt());
        oldUser.setPlayerId(newUser.getPlayerId());

        return userRepository.save(oldUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name="id", required=true) Long userId) throws Exception {
        User oldUser = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t delete user with id=" + userId));

        userRepository.delete(oldUser);
        return ResponseEntity.ok().build();
    }
}
