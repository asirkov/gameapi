package com.checkers.gameapi.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="player")
public class Player {
    @Id
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    private Long gamesCount;
    private Long winsCount;
    private Long losesCount;

    private byte[] avatarData;

    @NotBlank
    private Boolean online;
}
