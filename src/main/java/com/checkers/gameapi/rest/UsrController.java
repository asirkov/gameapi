package com.checkers.gameapi.rest;

import com.checkers.gameapi.dto.UsrDto;
import com.checkers.gameapi.repositories.UsrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class UsrController {
    @Autowired
    private UsrRepository usrRepository;

    @GetMapping("/users")
    public List<UsrDto> getUsersList() {
        return usrRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public UsrDto getUser(@PathVariable("id") Long userId) throws Exception {
        return usrRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t get user with id=" + userId));
    }

    @PostMapping("/users")
    public UsrDto postUser(@Valid @RequestBody UsrDto usrDto) {
        return usrRepository.save(usrDto);
    }

    @PutMapping("/users/{id}")
    public UsrDto putUser(@PathVariable("id") Long userId,
                          @Valid @RequestBody UsrDto newUsrDto) throws Exception {
        UsrDto oldUsrDto = usrRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t put user=" + newUsrDto.toString() +
                                                " to id=" + userId));

        oldUsrDto.setId(newUsrDto.getId());
        oldUsrDto.setUsername(newUsrDto.getUsername());
//        oldUser.setPasswordHash(newUser.getPasswordHash());
//        oldUser.setPasswordSalt(newUser.getPasswordSalt());

        return usrRepository.save(oldUsrDto);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name="id", required=true) Long userId) throws Exception {
        UsrDto oldUsrDto = usrRepository.findById(userId)
                .orElseThrow(() -> new Exception("Can`t delete user with id=" + userId));

        usrRepository.delete(oldUsrDto);
        return ResponseEntity.ok().build();
    }
}
