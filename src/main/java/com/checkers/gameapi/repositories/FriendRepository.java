package com.checkers.gameapi.repositories;

import com.checkers.gameapi.entities.FriendDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<FriendDto, Long> {
    List<FriendDto> findAllByPrimaryPlayerId(Long primaryPlayerId);
    FriendDto findByPrimaryPlayerIdAndId(Long primaryPlayerId, Long friendId);
}
