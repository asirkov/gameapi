package com.checkers.gameapi.service.impl;

import com.checkers.gameapi.model.Role;
import com.checkers.gameapi.model.Status;
import com.checkers.gameapi.model.Usr;
import com.checkers.gameapi.repositories.RoleRepository;
import com.checkers.gameapi.repositories.UsrRepository;
import com.checkers.gameapi.service.UsrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsrServiceImpl implements UsrService {
    private final UsrRepository usrRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usr register(Usr usr) {
        Role role = roleRepository.findByName("USER");
        usr.setRoles(List.of(role));

        usr.setPassword(passwordEncoder.encode(
                passwordEncoder.encode(usr.getPassword()) + usr.getSalt()));

        usr.setGamesCount(0L);
        usr.setWinsCount(0L);
        usr.setLosesCount(0L);

        usr.setStatus(Status.ACTIVE);
        usr.setOnline(true);

        Usr registeredUsr = usrRepository.save(usr);

        log.info("IN register - usr: {} successfully registered", registeredUsr.toString());
        return registeredUsr;
    }

    @Override
    public List<Usr> getAll() {
        List<Usr> result = usrRepository.findAll();
        log.info("IN geAll - {} usrs found", result.size());
        return result;
    }

    @Override
    public Usr findByUsrName(String usrName) {
        return null;
    }

    @Override
    public Usr findById(Long usrId) {
        return null;
    }

    @Override
    public void delete(Long usrId) {

    }
}

