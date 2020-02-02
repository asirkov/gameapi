package com.checkers.gameapi.controllers;

import com.checkers.gameapi.entities.PlayerDto;
import com.checkers.gameapi.models.Player;
import com.checkers.gameapi.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/players")
    public List<PlayerDto> getPlayersList() {
        return playerRepository.findAll();
    }

    @GetMapping("/players/{id}")
    public PlayerDto getPlayer(@PathVariable("id") Long playerId) throws Exception {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new Exception("Can`t get player with id=" + playerId));
    }

    @PostMapping("/players")
    public PlayerDto postPlayer(@Valid @RequestBody PlayerDto playerDto) {
        return playerRepository.save(playerDto);
    }

    @PutMapping("/players/{id}")
    public PlayerDto putPlayer(@PathVariable("id") Long playerId,
                               @Valid @RequestBody PlayerDto newPlayerDto) throws Exception {
        PlayerDto oldPlayerDto = playerRepository.findById(playerId)
                .orElseThrow(() -> new Exception("Can`t put player=" + newPlayerDto.toString() +
                        " to id=" + playerId));

//        oldPlayer.setId(newPlayer.getId());
        oldPlayerDto.setGamesCount(newPlayerDto.getGamesCount());
        oldPlayerDto.setWinsCount(newPlayerDto.getWinsCount());
        oldPlayerDto.setLosesCount(newPlayerDto.getLosesCount());
        oldPlayerDto.setAvatarData(newPlayerDto.getAvatarData());
        oldPlayerDto.setOnline(newPlayerDto.getOnline());
        oldPlayerDto.setUser(newPlayerDto.getUser());

        return playerRepository.save(oldPlayerDto);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(name="id", required=true) Long playerId) throws Exception {
        PlayerDto oldPlayerDto = playerRepository.findById(playerId)
                .orElseThrow(() -> new Exception("Can`t delete player with id=" + playerId));

        playerRepository.delete(oldPlayerDto);
        return ResponseEntity.ok().build();
    }
}

