package com.checkers.gameapi.repositories;

import com.checkers.gameapi.entities.PlayerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerDto, Long> {
}
