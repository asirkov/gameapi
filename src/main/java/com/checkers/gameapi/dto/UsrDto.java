package com.checkers.gameapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="usr")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value={"password", "passwordSalt"})
public class UsrDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usr_id")
    private Long id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "password_salt")
    private String passwordSalt;

    @NotNull
    @Column(name = "player_name")
    private String playerName;

    @NotNull
    @Column(name = "games_count")
    private Long gamesCount;

    @NotNull
    @Column(name = "wins_count")
    private Long winsCount;

    @NotNull
    @Column(name = "loses_count")
    private Long losesCount;

    @NotNull
    @Column(name = "avatar_data")
    private byte[] avatarData;

    @NotNull
    @Column(name = "active")
    private boolean active;

    @NotNull
    @Column(name = "update_time")
    private Long updateTime;
}


