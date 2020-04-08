package com.checkers.gameapi.dto.invitations;

import com.checkers.gameapi.dto.BaseDto;
import com.checkers.gameapi.model.InvitationEntity;
import com.checkers.gameapi.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
        {"id", "gameId", "fromUserId", "toUserId", "text", "status", "createdAt", "updatedAt"})
public class InvitationDto extends BaseDto {
    private Integer gameId;
    private Integer fromUserId;
    private Integer toUserId;
    private String text;
    private Status status;

    public static InvitationDto ofEntity(InvitationEntity invitationEntity) {
        return InvitationDto.builder()
                .id(invitationEntity.getId())
                .fromUserId(invitationEntity.getFromUsrId())
                .toUserId(invitationEntity.getToUsrId())
                .text(invitationEntity.getText())
                .status(invitationEntity.getStatus())
                .createdAt(invitationEntity.getCreatedAt())
                .updatedAt(invitationEntity.getUpdatedAt())
                .build();
    }
}
