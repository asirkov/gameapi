package com.checkers.gameapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDto {
    @Id
    @Column(name = "session_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;

    @NotNull
    @Column(name = "usr_id")
    private Long usrId;

    @NotNull
    @Column(name = "session_key")
    private Long sessionKey;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "active")
    private Boolean active;
}
