package com.checkers.gameapi.dao;

import com.checkers.gameapi.model.GameEntity;
import com.checkers.gameapi.model.InvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<GameEntity, Long> {
}
