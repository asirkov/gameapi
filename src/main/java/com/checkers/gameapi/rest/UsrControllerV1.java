package com.checkers.gameapi.rest;

import com.checkers.gameapi.dto.usrs.UsrDto;
import com.checkers.gameapi.dto.usrs.UsrResponseDto;
import com.checkers.gameapi.dto.usrs.UsrsListResponseDto;
import com.checkers.gameapi.model.UsrEntity;
import com.checkers.gameapi.repositories.UsrsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UsrControllerV1 {
    private final UsrsRepository usrsRepository;

    @GetMapping("/users")
    public ResponseEntity<UsrsListResponseDto> getUsersList(
            @RequestParam(name = "q", required = false) String searchQuery,
            @RequestParam(name = "online", required = false) Boolean online
            ) {
        List<UsrDto> usrDtoList = usrsRepository.findAll().stream()
                .map(UsrDto::ofEntity)
                .collect(Collectors.toUnmodifiableList());

        if (nonNull(searchQuery)) {
            usrDtoList = usrDtoList.stream()
                    .filter(usrDto -> usrDto.getPlayerName().contains(searchQuery))
                    .collect(Collectors.toList());
        }
        if (nonNull(online)) {
            usrDtoList = usrDtoList.stream()
                    .filter(usrDto -> usrDto.isOnline() == online)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok().body(new UsrsListResponseDto(null, null, usrDtoList.size(), usrDtoList));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UsrResponseDto> getUser(@PathVariable("id") Long userId) throws NoSuchElementException {
        final Optional<UsrEntity> usrEntityOpt = usrsRepository.findById(userId);

        if (usrEntityOpt.isPresent())
            return ResponseEntity.ok().body(new UsrResponseDto(null, null, UsrDto.ofEntity(usrEntityOpt.get())));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UsrResponseDto(null, String.format("Can`t get user with id=%s", userId), null));
    }

    @GetMapping("/users/{id}/avatar")
    public ResponseEntity<String> getUserAvatar(@PathVariable("id") Long userId) throws NoSuchElementException {
        final Optional<UsrEntity> usrEntityOpt = usrsRepository.findById(userId);

        if (usrEntityOpt.isPresent())
            return ResponseEntity.ok().body(Base64.getEncoder().encodeToString(usrEntityOpt.get().getAvatarData()));
        else
            return ResponseEntity.notFound().build();
    }
}
