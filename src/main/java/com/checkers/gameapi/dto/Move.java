package com.checkers.gameapi.dto;

import com.checkers.gameapi.dto.enums.MoveType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="move")
public class Move {
    @Id
    @NotBlank
    private Long id;

    @NotBlank
    private Long gameId;

    @NotBlank
    private MoveType moveType;

    private String moveFrom;
    private String moveTo;

    private String killedChecker;
}
