package com.checkers.gameapi.repositories;

import com.checkers.gameapi.model.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<FriendEntity, Long> {
    public List<FriendEntity> findAllByPrimaryUsrId(Long primaryUsrId);
}
