package com.checkers.gameapi.repositories;

import com.checkers.gameapi.entities.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
    public UserDto getUserDtoByUsername(String userName);
}
