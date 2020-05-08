package com.checkers.gameapi.rest;

import com.checkers.gameapi.dao.GamesRepository;
import com.checkers.gameapi.dao.UsrsRepository;
import com.checkers.gameapi.dto.games.*;
import com.checkers.gameapi.model.GameEntity;
import com.checkers.gameapi.model.MoveEntity;
import com.checkers.gameapi.model.UsrEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GamesControllerV1 {
    private final GamesRepository gamesRepository;
    private final UsrsRepository usrsRepository;

    @GetMapping("/games")
    public ResponseEntity<GamesListResponseDto> getGamesList(
//            @RequestHeader(name = "auth") String authToken,
    ) {
        List<GameDto> GameDtoList = gamesRepository.findAll().stream()
                .map(GameDto::ofEntity)
                .collect(Collectors.toUnmodifiableList());

        // TODO: return token
        return ResponseEntity.ok().body(new GamesListResponseDto(null, null, GameDtoList.size(), GameDtoList));
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<GameResponseDto> getGame(
//            @RequestHeader(name = "auth") String authToken,
            @PathVariable("id") Long GameId
    ) {
        final Optional<GameEntity> GameEntityOpt = gamesRepository.findById(GameId);

        if (GameEntityOpt.isPresent()) {
            // TODO: return token
            return ResponseEntity.ok().body(new GameResponseDto(null, null, GameDto.ofEntity(GameEntityOpt.get())));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GameResponseDto(null, String.format("Can`t get Game with id=%s", GameId), null));
        }
    }

    @GetMapping("/games/{id}/moves")
    public ResponseEntity<MovesListResponseDto> getGameMoves(
//            @RequestHeader(name = "auth") String authToken,
            @PathVariable("id") Long GameId
    ) {
        final Optional<GameEntity> gameEntityOpt = gamesRepository.findById(GameId);

        if (gameEntityOpt.isPresent()) {
//            return ResponseEntity.ok().body(Base64.getEncoder().encodeToString(GameEntityOpt.get().getAvatarData()));
            final List<MoveEntity> movesEntities = gameEntityOpt.get().getMoves();
            final List<MoveDto> movesDtos = movesEntities.stream()
                    .map(MoveDto::ofEntity)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(new MovesListResponseDto(null, null, movesDtos.size(), movesDtos));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MovesListResponseDto(null, String.format("Can`t get Game with id=%s", GameId), null, null));
        }
    }

    @PostMapping("/games")
    public ResponseEntity<GameResponseDto> createGame(
//            @RequestHeader(name = "auth") String authToken,
        @RequestBody GameDto newGameDto
    ) {
        Optional<UsrEntity> newGameParticipant1 = usrsRepository.findById(newGameDto.getParticipant1().getId());
        Optional<UsrEntity> newGameParticipant2 = usrsRepository.findById(newGameDto.getParticipant2().getId());

        if (newGameParticipant1.isPresent() && newGameParticipant2.isPresent()) {
            GameEntity newGameEntity = GameEntity.builder()
                    .id(newGameDto.getId())
                    .participant1(newGameParticipant1.get())
                    .participant2(newGameParticipant2.get())
                    .result(newGameDto.getResult())
                    .log(newGameDto.getLog())
                    .startTime(newGameDto.getStartTime())
                    .build();

            final GameEntity savedGameEntity = gamesRepository.save(newGameEntity);

            return ResponseEntity.ok().body(new GameResponseDto(null, null, GameDto.ofEntity(savedGameEntity)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GameResponseDto(null, String.format("Can`t get participants of game %s", newGameDto.toString()), null));
        }
    }
}