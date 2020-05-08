package com.checkers.gameapi.dao;

import com.checkers.gameapi.model.UsrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsrsRepository extends JpaRepository<UsrEntity, Long> {
    UsrEntity findByUsrName(String userName);
    List<UsrEntity> findByOnline(boolean online);
}
