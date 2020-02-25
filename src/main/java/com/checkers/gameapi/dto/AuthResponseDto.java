package com.checkers.gameapi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponseDto {
    private final String usrName;
    private final String token;
}
