package com.checkers.gameapi.model;

import com.checkers.gameapi.model.enums.GameResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@ToString
@SuperBuilder
@Getter @Setter
@Entity @Table(name = "games")
@NoArgsConstructor
public class GameEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UsrEntity.class)
    @JoinColumn(name = "participant1", referencedColumnName = "id", nullable = false)
    private UsrEntity participant1;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UsrEntity.class)
    @JoinColumn(name = "participant2", referencedColumnName = "id", nullable = false)
    private UsrEntity participant2;

    @Column(name = "log")
    private String log;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "result")
    @Enumerated(value = EnumType.STRING)
    private GameResult result;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = MoveEntity.class)
    @JoinColumn(name = "id", referencedColumnName = "game_id")
    private List<MoveEntity> moves;
}
