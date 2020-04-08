package com.checkers.gameapi.rest;

import com.checkers.gameapi.dto.invitations.InvitationDto;
import com.checkers.gameapi.dto.invitations.InvitationsListResponseDto;
import com.checkers.gameapi.model.InvitationEntity;
import com.checkers.gameapi.model.enums.Status;
import com.checkers.gameapi.repositories.InvitationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InvitationControllerV1 {
    final InvitationsRepository invitationsRepository;

    @GetMapping("/invitations")
    public InvitationsListResponseDto getInvitations(
//            @RequestHeader(name = "auth") String authToken,
            @RequestParam(name = "userToId") Integer userToId,
            @RequestParam(name = "includeInactive", required = false, defaultValue = "false") Boolean includeInactive,
            @RequestParam(name = "includeDeleted", required = false, defaultValue = "false") Boolean includeDeleted
    ) {
        // TODO: resolve token

        final List<Status> invitationStatuses = new ArrayList<>(List.of(Status.ACTIVE));

        if (includeInactive)
            invitationStatuses.add(Status.INACTIVE);
        if (includeDeleted)
            invitationStatuses.add(Status.DELETED);

        final List<InvitationEntity> usersInvitationsList = invitationsRepository.findByToUsrIdAndStatusIn(userToId, invitationStatuses);
        final List<InvitationDto> usersInvitationsDtoList = usersInvitationsList.stream()
                .map(InvitationDto::ofEntity)
                .collect(Collectors.toUnmodifiableList());


        return new InvitationsListResponseDto(null, null, usersInvitationsDtoList.size(), usersInvitationsDtoList);
    }

//    @PostMapping("/invitations")
//    public InvitationsListResponseDto saveInvitation(@RequestBody @Valid InvitationDto newInvitation) {
//
//    }
}
