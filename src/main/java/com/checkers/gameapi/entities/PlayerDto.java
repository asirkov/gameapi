package com.checkers.gameapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="player")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class PlayerDto {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private Long gamesCount;
    private Long winsCount;
    private Long losesCount;

    private byte[] avatarData;

    private Boolean online;

    @OneToOne
    private UserDto user;
}
