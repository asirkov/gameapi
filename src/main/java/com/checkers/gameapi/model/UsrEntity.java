package com.checkers.gameapi.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@ToString
@Builder
@Getter@Setter
@Entity@Table(name = "usr")
@AllArgsConstructor
public class UsrEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "usr_name")
    private String usrName;
    @Column(name = "player_name")
    private String playerName;

    @Column(name = "password")
    private String password;

    @Column(name = "games_count")
    private Long gamesCount;
    @Column(name = "wins_count")
    private Long winsCount;
    @Column(name = "loses_count")
    private Long losesCount;

    @Column(name = "avatar_data")
    private byte[] avatarData;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private SessionEntity session;

    @Column(name = "online")
    private Boolean online;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    public UsrEntity() {
    }
}
