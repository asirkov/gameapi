package com.checkers.gameapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@EqualsAndHashCode
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    public BaseDto() {
    }
}
