package com.checkers.gameapi.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="friend")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
