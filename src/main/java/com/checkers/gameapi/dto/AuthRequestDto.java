package com.checkers.gameapi.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String usrName;
    private String password;
}
