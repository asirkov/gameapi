package com.checkers.gameapi.repositories;

import com.checkers.gameapi.model.Usr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsrRepository extends JpaRepository<Usr, Long> {
    public Usr findByUserName(String userName);
}
