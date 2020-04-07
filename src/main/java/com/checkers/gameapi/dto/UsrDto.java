package com.checkers.gameapi.dto;

import com.checkers.gameapi.model.UsrEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
        {"id", "playerName","gamesCount", "winsCount", "losesCount", "online", "createdAt", "updatedAt" })
public class UsrDto extends BaseDto{
    private String playerName;

    private Long gamesCount;
    private Long winsCount;
    private Long losesCount;

    private boolean online;

    public static UsrDto ofEntity(UsrEntity usrEntity) {
        return UsrDto.builder()
                .id(usrEntity.getId())
                .playerName(usrEntity.getPlayerName())
                .gamesCount(usrEntity.getGamesCount())
                .winsCount(usrEntity.getWinsCount())
                .losesCount(usrEntity.getLosesCount())
                .online(usrEntity.getOnline())
                .createdAt(usrEntity.getCreatedAt())
                .updatedAt(usrEntity.getCreatedAt())
                .build();
    }
}
