package com.checkers.gameapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsrDto extends BaseDto {
    private String usrName;
    private String playerName;
    private String hashedPassword;

    private Long gamesCount;
    private Long winsCount;
    private Long losesCount;

    private byte[] avatarData;

    private boolean online;
}
