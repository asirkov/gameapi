package com.checkers.gameapi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponseDto {
    private final String token;
    private final String message;
    private final Long id;
    private final Boolean authorized;
}
