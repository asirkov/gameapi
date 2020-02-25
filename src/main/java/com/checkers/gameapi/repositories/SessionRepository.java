package com.checkers.gameapi.repositories;

import com.checkers.gameapi.model.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    public Optional<SessionEntity> findByUsrId(Long usrId);
}
