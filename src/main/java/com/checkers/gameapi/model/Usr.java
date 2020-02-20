package com.checkers.gameapi.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "usr")
@ToString
public class Usr extends BaseEntity {

    @Column(name = "user_name")
    private String userName;
    @Column(name = "player_name")
    private String playerName;

    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;

    @Column(name = "games_count")
    private Long gamesCount;
    @Column(name = "wins_count")
    private Long winsCount;
    @Column(name = "loses_count")
    private Long losesCount;

    @Column(name = "avatar_data")
    private byte[] avatarData;

    @Column(name = "online")
    private boolean online;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "usr_id", referencedColumnName = "usr_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private List<Role> roles;
}
