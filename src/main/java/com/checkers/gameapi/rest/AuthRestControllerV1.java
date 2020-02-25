package com.checkers.gameapi.rest;

import com.checkers.gameapi.dto.AuthRequestDto;
import com.checkers.gameapi.dto.AuthResponseDto;
import com.checkers.gameapi.dto.UsrDto;
import com.checkers.gameapi.model.SessionEntity;
import com.checkers.gameapi.model.UsrEntity;
import com.checkers.gameapi.repositories.SessionRepository;
import com.checkers.gameapi.repositories.UsrRepository;
import com.checkers.gameapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestControllerV1 {
    private final JwtTokenProvider jwtTokenProvider;

    private final UsrRepository usrRepository;
    private final SessionRepository sessionRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequestDto requestDto) {
        try {
            String usrName = requestDto.getUsrName();

            UsrEntity usr = usrRepository.findByUsrName(usrName);

            if (isNull(usr)) {
                log.error("IN login - usrName {} not found", requestDto.getUsrName());
                return ResponseEntity.badRequest().build();

            } else {
                if (!requestDto.getPassword().equals(usr.getPassword())) {
                    log.error("IN login - wrong password for usrName {}", requestDto.getUsrName());
                    return ResponseEntity.badRequest().build();
                }

//                SessionEntity session = usr.get().getSession();

                String token = jwtTokenProvider.createToken(usr);
                AuthResponseDto response = new AuthResponseDto(usrName, token);
                return ResponseEntity.ok(response);
            }
        } catch (AuthenticationException aEx) {
            throw new BadCredentialsException("Invalid usrName or password.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsrDto usrDto) {

        UsrEntity existingUsr = usrRepository.findByUsrName(usrDto.getUsrName());

        if (nonNull(existingUsr)) {
            log.info("IN register - usr with name {} already exists", usrDto.getUsrName());
            return ResponseEntity.badRequest().body("Usr with usrName already exists");
        }

        UsrEntity newUsr = UsrEntity.builder()
                .usrName(usrDto.getUsrName())
                .playerName(usrDto.getPlayerName())
                .password(usrDto.getHashedPassword())
                .avatarData(usrDto.getAvatarData())
                .gamesCount(0L)
                .losesCount(0L)
                .winsCount(0L)
                .online(false)
                .build();

        UsrEntity savedUsr = usrRepository.save(newUsr);

        return ResponseEntity.ok("Usr successfully created");
    }
}
