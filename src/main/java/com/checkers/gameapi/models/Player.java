package com.checkers.gameapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Player {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private Long gamesCount;
    @JsonProperty
    private Long winsCount;
    @JsonProperty
    private Long losesCount;

    @JsonProperty
    private byte[] avatarData;

    @JsonProperty
    private Boolean online;

    @JsonProperty
    private User user;
}
