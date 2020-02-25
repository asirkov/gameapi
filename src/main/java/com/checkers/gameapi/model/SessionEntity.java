package com.checkers.gameapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
@Builder(builderClassName = "Builder")
@Table(name = "session")
public class SessionEntity extends BaseEntity {
    @Column(name = "session_key")
    private String sessionKey;

    @OneToOne(mappedBy = "session", fetch = FetchType.LAZY)
    private UsrEntity usr;
}
