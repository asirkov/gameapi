package com.checkers.gameapi.rest;

import com.checkers.gameapi.dto.usrs.UsrDto;
import com.checkers.gameapi.dto.usrs.UsrResponseDto;
import com.checkers.gameapi.dto.usrs.UsrsListResponseDto;
import com.checkers.gameapi.model.UsrEntity;
import com.checkers.gameapi.dao.UsrsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.sql.SQLException;
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
//            @RequestHeader(name = "auth") String authToken,
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
                    .filter(usrDto -> usrDto.getOnline() == online)
                    .collect(Collectors.toList());
        }

        // TODO: return token
        return ResponseEntity.ok().body(new UsrsListResponseDto(null, null, usrDtoList.size(), usrDtoList));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UsrResponseDto> getUser(
//            @RequestHeader(name = "auth") String authToken,
            @PathVariable("id") Long userId
    ) {
        final Optional<UsrEntity> usrEntityOpt = usrsRepository.findById(userId);

        if (usrEntityOpt.isPresent()) {
            // TODO: return token
            return ResponseEntity.ok().body(new UsrResponseDto(null, null, UsrDto.ofEntity(usrEntityOpt.get())));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UsrResponseDto(null, String.format("Can`t get user with id=%s", userId), null));
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsrResponseDto> updateUser(
//            @RequestHeader(name = "auth") String authToken,
            @RequestBody UsrDto newUsr,
            @PathVariable("id") Long userId
    ) {
        final Optional<UsrEntity> existingUsrOpt = usrsRepository.findById(userId);

        if (existingUsrOpt.isPresent()) {
            final UsrEntity existingUsr = existingUsrOpt.get();

            existingUsr.setPlayerName(newUsr.getPlayerName());
            existingUsr.setGamesCount(newUsr.getGamesCount());
            existingUsr.setWinsCount(newUsr.getWinsCount());
            existingUsr.setLosesCount(newUsr.getLosesCount());
            existingUsr.setOnline(newUsr.getOnline());

            final UsrDto updatedUsr = UsrDto.ofEntity(usrsRepository.save(existingUsr));

            // TODO: return token
            return ResponseEntity.ok().body(new UsrResponseDto(null, null, updatedUsr));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UsrResponseDto(null, String.format("Can`t update user with id=%s", newUsr.getId()), null));
        }
    }

    @GetMapping("/users/{id}/avatar")
    public ResponseEntity<String> getUserAvatar(
//            @RequestHeader(name = "auth") String authToken,
            @PathVariable("id") Long userId
    ) {
        final Optional<UsrEntity> usrEntityOpt = usrsRepository.findById(userId);

        if (usrEntityOpt.isPresent())
            return ResponseEntity.ok().body(Base64.getEncoder().encodeToString(usrEntityOpt.get().getAvatarData()));
        else
            return ResponseEntity.notFound().build();
    }

    //TODO: add update for avatar
}
