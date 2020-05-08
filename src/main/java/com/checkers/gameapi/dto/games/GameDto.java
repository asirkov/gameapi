package com.checkers.gameapi.dto.games;

import com.checkers.gameapi.dto.BaseDto;
import com.checkers.gameapi.dto.usrs.UsrDto;
import com.checkers.gameapi.model.GameEntity;
import com.checkers.gameapi.model.enums.GameResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
        {"id", "participant1Id", "participant2Id", "startTime", "result", "log"})
public class GameDto extends BaseDto {
//    private long participant1Id;
//    private long participant2Id;
    private UsrDto participant1;
    private UsrDto participant2;
    private Timestamp startTime;
    private GameResult result;
    private String log;
    private List<MoveDto> moves;

    public static GameDto ofEntity(GameEntity gameEntity) {
        final GameDtoBuilder<?, ?> builder = GameDto.builder()
                .id(gameEntity.getId())
                .participant1(UsrDto.ofEntity(gameEntity.getParticipant1()))
                .participant2(UsrDto.ofEntity(gameEntity.getParticipant2()))
                .startTime(gameEntity.getStartTime())
                .result(gameEntity.getResult())
                .log(gameEntity.getLog())

                .createdAt(gameEntity.getCreatedAt())
                .updatedAt(gameEntity.getUpdatedAt());

        if (gameEntity.getMoves() != null && gameEntity.getMoves().size() > 0) {
            builder.moves(gameEntity.getMoves().stream()
                    .map(MoveDto::ofEntity)
                    .collect(Collectors.toList())
            );
        }

        return builder.build();
    }
}
