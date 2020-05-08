package com.checkers.gameapi.rest;

import com.checkers.gameapi.dao.GamesRepository;
import com.checkers.gameapi.dao.InvitationsRepository;
import com.checkers.gameapi.dao.UsrsRepository;
import com.checkers.gameapi.dto.invitations.InvitationDto;
import com.checkers.gameapi.dto.invitations.InvitationResponseDto;
import com.checkers.gameapi.dto.invitations.InvitationsListResponseDto;
import com.checkers.gameapi.model.GameEntity;
import com.checkers.gameapi.model.InvitationEntity;
import com.checkers.gameapi.model.UsrEntity;
import com.checkers.gameapi.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InvitationControllerV1 {
    final InvitationsRepository invitationsRepository;
    final GamesRepository gamesRepository;
    final UsrsRepository usrsRepository;

    @GetMapping("/invitations")
    public ResponseEntity<InvitationsListResponseDto> getInvitations(
//            @RequestHeader(name = "auth") String authToken,
            @RequestParam(name = "userToId", required = false) Long userToId,
            @RequestParam(name = "includeInactive", required = false, defaultValue = "false") Boolean includeInactive,
            @RequestParam(name = "includeDeleted", required = false, defaultValue = "false") Boolean includeDeleted
    ) {
        // TODO: resolve token

        final List<Status> invitationStatuses = new ArrayList<>(List.of(Status.ACTIVE));

        if (includeInactive)
            invitationStatuses.add(Status.INACTIVE);
        if (includeDeleted)
            invitationStatuses.add(Status.DELETED);

        // TODO: handle statuses list
        final List<InvitationEntity> usersInvitationsList = invitationsRepository.findByToUsrIdAndStatusIsIn(userToId, invitationStatuses);
        final List<InvitationDto> usersInvitationsDtoList = usersInvitationsList.stream()
                .map(InvitationDto::ofEntity)
                .collect(Collectors.toUnmodifiableList());

        // TODO: return token
        return ResponseEntity.ok().body(new InvitationsListResponseDto(null, null, usersInvitationsDtoList.size(), usersInvitationsDtoList));
    }

    @GetMapping("/invitation/{id}")
    public ResponseEntity<InvitationResponseDto> getInvitations(
//            @RequestHeader(name = "auth") String authToken,
        @PathVariable Long invitationId
    ) {
        final Optional<InvitationEntity> invitationEntityOpt = invitationsRepository.findById(invitationId);

        if (invitationEntityOpt.isPresent()) {
            // TODO: return token
            return ResponseEntity.ok().body(new InvitationResponseDto(null, null, InvitationDto.ofEntity(invitationEntityOpt.get())));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new InvitationResponseDto(null, String.format("Can`t get invitation with id=%s", invitationId), null));
        }
    }

    @PutMapping("/invitation/{id}")
    public ResponseEntity<InvitationResponseDto> getInvitations(
//            @RequestHeader(name = "auth") String authToken,
            @RequestBody InvitationDto invitationDto,
            @PathVariable Long invitationId
    ) {
        final Optional<InvitationEntity> existingInvitationEntityOpt = invitationsRepository.findById(invitationId);

        if (existingInvitationEntityOpt.isPresent()) {
            final InvitationEntity existingInvitation = existingInvitationEntityOpt.get();

            final Optional<UsrEntity> fromUsrEntityOpt = usrsRepository.findById(invitationDto.getFromUserId());
            final Optional<UsrEntity> toUsrEntityOpt = usrsRepository.findById(invitationDto.getToUserId());
            final Optional<GameEntity> gameEntityOpt = gamesRepository.findById(invitationDto.getGameId());

            if (fromUsrEntityOpt.isPresent() && toUsrEntityOpt.isPresent() && gameEntityOpt.isPresent()) {
                final UsrEntity fromUsrEntity = fromUsrEntityOpt.get();
                final UsrEntity toUsrEntity = toUsrEntityOpt.get();
                final GameEntity gameEntity = gameEntityOpt.get();

                existingInvitation.setFromUsr(fromUsrEntity);
                existingInvitation.setToUsr(toUsrEntity);
                existingInvitation.setGame(gameEntity);
                existingInvitation.setText(invitationDto.getText());
                existingInvitation.setStatus(invitationDto.getStatus());

                final InvitationDto updatedInvitation = InvitationDto.ofEntity(invitationsRepository.save(existingInvitation));

                // TODO: return token
                return ResponseEntity.ok().body(new InvitationResponseDto(null, null, updatedInvitation));

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new InvitationResponseDto(null, String.format("Can`t get game/usrFrom/usrTo for invitation with id=%s", invitationId), null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new InvitationResponseDto(null, String.format("Can`t update invitation with id=%s", invitationId), null));
        }
    }


    @PostMapping("/invitations")
    public ResponseEntity<InvitationResponseDto> saveInvitation(
//            @RequestHeader(name = "auth") String authToken,
            @RequestBody @Valid InvitationDto newInvitation
    ) {
        Optional<UsrEntity> fromUsrOpt = nonNull(newInvitation.getFromUserId())
                ? usrsRepository.findById(newInvitation.getFromUserId())
                : Optional.empty();

        Optional<UsrEntity> toUsrOpt = nonNull(newInvitation.getToUserId())
                ? usrsRepository.findById(newInvitation.getToUserId())
                : Optional.empty();

        Optional<GameEntity> gameOpt = nonNull(newInvitation.getGameId())
                ? gamesRepository.findById(newInvitation.getGameId())
                : Optional.empty();


        final InvitationEntity.InvitationEntityBuilder<?, ?> newInvitationEntityBuilder = InvitationEntity.builder()
                .status(newInvitation.getStatus())
                .createdAt(newInvitation.getCreatedAt())
                .updatedAt(newInvitation.getUpdatedAt());

        fromUsrOpt.ifPresent(newInvitationEntityBuilder::fromUsr);
        toUsrOpt.ifPresent(newInvitationEntityBuilder::toUsr);
        gameOpt.ifPresent(newInvitationEntityBuilder::game);

        final InvitationDto savedInvitationDto = InvitationDto.ofEntity(invitationsRepository.save(newInvitationEntityBuilder.build()));
        // TODO: return token
        return ResponseEntity.ok().body(new InvitationResponseDto(null, null, savedInvitationDto));

//        else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new InvitationResponseDto(null, "Wrong from/to user Id or gameId in invitation", null));
//        }
    }
}
