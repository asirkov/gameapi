package com.checkers.gameapi.model;

import com.checkers.gameapi.model.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@ToString
@SuperBuilder
@Getter @Setter
@Entity
@Table(name = "invitations",
       uniqueConstraints = { @UniqueConstraint(columnNames = {"from_usr_id", "to_usr_id", "game_id"}) })
@NoArgsConstructor
public class InvitationEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = GameEntity.class)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private GameEntity game;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UsrEntity.class)
    @JoinColumn(name = "from_usr_id", referencedColumnName = "id", nullable = false)
    private UsrEntity fromUsr;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UsrEntity.class)
    @JoinColumn(name = "to_usr_id", referencedColumnName = "id", nullable = false)
    private UsrEntity toUsr;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "invitation_status", nullable = false)
    private Status status;
}
