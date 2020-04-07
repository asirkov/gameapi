package com.checkers.gameapi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@ToString
@SuperBuilder
@Getter @Setter
@Entity @Table(name = "invitations")
@NoArgsConstructor
@AllArgsConstructor
public class InvitationEntity extends BaseEntity {

}
