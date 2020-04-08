package com.checkers.gameapi.repositories;

import com.checkers.gameapi.model.InvitationEntity;
import com.checkers.gameapi.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationsRepository  extends JpaRepository<InvitationEntity, Long> {
    List<InvitationEntity> findByToUsrIdAndStatusIn(Integer toUsrId, List<Status> statuses);
}
