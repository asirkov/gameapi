package com.checkers.gameapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@ToString
@SuperBuilder
@Getter @Setter
@Entity @Table(name = "friends")
@AllArgsConstructor
public class FriendEntity extends BaseEntity {
    @Column(name = "primary_usr_id")
    private Long primaryUsrId;

    @OneToOne
    @JoinColumn(name = "secondary_usr_id", referencedColumnName = "id")
    private UsrEntity friend;
}
