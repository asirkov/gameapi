package com.checkers.gameapi.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @NotBlank
    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String passwordHash;
    @NotBlank
    private String passwordSalt;

    private Long playerId;
}
