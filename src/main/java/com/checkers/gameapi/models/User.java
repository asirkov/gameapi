package com.checkers.gameapi.models;

import com.checkers.gameapi.entities.UserDto;
import com.checkers.gameapi.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String login;
}
