package com.checkers.gameapi.entities;


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

    @Transient
    private Player participant1;
    @Transient
    private Player participant2;

    private Timestamp startTime;
}
