package com.checkers.gameapi.dto.games;

import com.checkers.gameapi.dto.BaseDto;
import com.checkers.gameapi.model.MoveEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
        {"participant1Id", "participant2Id", "startTime", "result", "log"})
public class MoveDto extends BaseDto {
    private String from;
    private String to;
    private String kill;
    private String log;
    private long gameId;

    public static MoveDto ofEntity(MoveEntity moveEntity) {
        return MoveDto.builder()
                .id(moveEntity.getId())
                .from(moveEntity.getFrom())
                .to(moveEntity.getTo())
                .kill(moveEntity.getKill())
                .log(moveEntity.getLog())
                .gameId(moveEntity.getGame().getId())
                .createdAt(moveEntity.getCreatedAt())
                .updatedAt(moveEntity.getUpdatedAt())
                .build();
    }
}