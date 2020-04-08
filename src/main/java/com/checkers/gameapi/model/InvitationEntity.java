package com.checkers.gameapi.model;

import com.checkers.gameapi.model.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@ToString
@SuperBuilder
@Getter @Setter
@Entity @Table(name = "invitations")
@NoArgsConstructor
public class InvitationEntity extends BaseEntity {
    @Column(name = "game_id", nullable = false)
    Integer gameId;

    @Column(name = "from_usr_id", nullable = false)
    Integer fromUsrId;
    @Column(name = "to_usr_id", nullable = false)
    Integer toUsrId;

    @Column(name = "text")
    String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "invitation_status", nullable = false)
    private Status status;
}
