package com.checkers.gameapi.rest;

import com.checkers.gameapi.dto.FriendDto;
import com.checkers.gameapi.repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class FriendController {
    @Autowired
    FriendRepository friendRepository;

    @GetMapping("/friends")
    public List<FriendDto> getFriendsList() {
        return friendRepository.findAll();
    }

    @GetMapping("/friends/{id}")
    public FriendDto getFriend(@PathVariable("id") Long friendId) throws Exception {
        return friendRepository.findById(friendId)
                .orElseThrow(() -> new Exception("Can`t get friend with id=" + friendId));
    }

    @GetMapping("/players/{id}/friends")
    public List<FriendDto> getPlayersFriendsList(@PathVariable("id") Long playerId) {
        return friendRepository.findAllByPrimaryPlayerId(playerId);
    }

    @GetMapping("/players/{primary_id}/friends/{friend_id}")
    public FriendDto getPlayersFriend(@PathVariable("primary_id") Long primaryPlayerId,
                                      @PathVariable("friend_id") Long friendId) {
        return friendRepository.findByPrimaryPlayerIdAndId(primaryPlayerId, friendId);
    }

}
