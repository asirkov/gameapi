package com.checkers.gameapi.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;

import java.sql.Blob;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequestDto {
    private String userName;
    private String playerName;
    private String passwordHash;
    private byte[] avatarData;
}
