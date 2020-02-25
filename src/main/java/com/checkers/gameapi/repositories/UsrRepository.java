package com.checkers.gameapi.repositories;

import com.checkers.gameapi.model.UsrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsrRepository extends JpaRepository<UsrEntity, Long> {
    public UsrEntity findByUsrName(String userName);
}
