package com.checkers.gameapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@ToString
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "games")
@NoArgsConstructor
public class MoveEntity extends BaseEntity {
    @Column(name = "from", nullable = false)
    private String from;

    @Column(name = "to", nullable = false)
    private String to;

    @Column(name = "kill")
    private String kill;

    @Column(name = "log")
    private String log;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = GameEntity.class)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private GameEntity game;
}
