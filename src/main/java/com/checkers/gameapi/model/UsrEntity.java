package com.checkers.gameapi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@ToString
@SuperBuilder
@Getter @Setter
@Entity @Table(name = "usrs")
@NoArgsConstructor
@AllArgsConstructor
public class UsrEntity extends BaseEntity {
    @Column(name = "usr_name", unique = true, nullable = false)
    private String usrName;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "games_count")
    private Long gamesCount;
    @Column(name = "wins_count")
    private Long winsCount;
    @Column(name = "loses_count")
    private Long losesCount;

    @Lob
    @Column(name = "avatar_data")
    private byte[] avatarData;


    @Column(name = "online")
    private Boolean online;
}
