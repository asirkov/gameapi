package com.checkers.gameapi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="friend")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendDto {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @JsonIgnore
    private Long primaryPlayerId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="secondary_player_id")
    private PlayerDto friend;
}
