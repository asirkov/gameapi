package com.checkers.gameapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value={"passwordHash", "passwordSalt"})
public class UserDto {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String login;

    @NotNull
    @JsonIgnore
    private String passwordHash;

    @NotNull
    @JsonIgnore
    private String passwordSalt;
}


