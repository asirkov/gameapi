package com.checkers.gameapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Date;

@Data
@EqualsAndHashCode
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDto {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public BaseDto() {
    }
}
