package com.checkers.gameapi.repositories;


import com.checkers.gameapi.entities.Game;
import com.checkers.gameapi.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    public List<Game> getAllByParticipant1IdOrParticipant2Id(@NotBlank Long participant1Id, @NotBlank Long participant2Id);

}