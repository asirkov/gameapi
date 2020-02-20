package com.checkers.gameapi.service;


import com.checkers.gameapi.model.Usr;

import java.util.List;

public interface UsrService {
    Usr register(Usr user);

    List<Usr> getAll();

    Usr findByUsrName(String usrName);
    Usr findById(Long usrId);
    void delete(Long usrId);
}
