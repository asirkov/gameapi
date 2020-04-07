package com.checkers.gameapi.rest;

import com.checkers.gameapi.dto.UsrDto;
import com.checkers.gameapi.dto.UsrResponseDto;
import com.checkers.gameapi.dto.UsrsListResponseDto;
import com.checkers.gameapi.model.UsrEntity;
import com.checkers.gameapi.repositories.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UsrControllerV1 {
    private final UsrRepository usrRepository;

    @GetMapping("/users")
    public UsrsListResponseDto getUsersList(
            @RequestParam(name = "q", required = false) String searchQuery,
            @RequestParam(name = "online", required = false) Boolean online
            ) {
        List<UsrDto> usrDtoList = usrRepository.findAll().stream()
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

        return new UsrsListResponseDto(null, null, usrDtoList.size(), usrDtoList);
    }

    @GetMapping("/users/{id}")
    public UsrResponseDto getUser(@PathVariable("id") Long userId) throws NoSuchElementException {
        final Optional<UsrEntity> usrEntity = usrRepository.findById(userId);

        return usrEntity.map(entity -> new UsrResponseDto(null, null, UsrDto.ofEntity(entity)))
                .orElseGet(() -> new UsrResponseDto(null, String.format("Can`t get user with id=%s", userId), null));
    }
}
