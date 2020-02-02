package com.checkers.gameapi.entities;


import com.checkers.gameapi.enums.GameResult;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="game")
public class Game {
    @Id
    @NotBlank
    private Long id;

    @NotBlank
    private Long participant1Id;
    @NotBlank
    private Long participant2Id;

    private GameResult result;

    private Timestamp startTime;
}
