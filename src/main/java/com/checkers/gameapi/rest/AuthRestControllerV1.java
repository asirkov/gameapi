package com.checkers.gameapi.rest;

import com.checkers.gameapi.dto.auth.AuthRequestDto;
import com.checkers.gameapi.dto.auth.AuthResponseDto;
import com.checkers.gameapi.dto.auth.RegisterRequestDto;
import com.checkers.gameapi.dto.auth.RegisterResponseDto;
import com.checkers.gameapi.model.UsrEntity;
import com.checkers.gameapi.repositories.UsrsRepository;
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

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestControllerV1 {
    private final JwtTokenProvider jwtTokenProvider;

    private final UsrsRepository usrsRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequestDto requestDto) {
        try {
            String usrName = requestDto.getUsrName();

            UsrEntity usr = usrsRepository.findByUsrName(usrName);

            if (isNull(usr)) {
                log.error("IN login - usrName {} not found", requestDto.getUsrName());
                return ResponseEntity
                        .badRequest()
                        .body(new AuthResponseDto(null, String.format("Can`t find user with name %s", usrName), null, false));

            } else {
                if (!requestDto.getPassword().equals(usr.getPassword())) {
                    log.error("IN login - wrong password for usrName {}", requestDto.getUsrName());
                    return ResponseEntity
                            .badRequest()
                            .body(new AuthResponseDto(null, "Wrong password for usrName", null,false));
                }

//                SessionEntity session = usr.get().getSession();

                String token = jwtTokenProvider.createToken(usr);
                return ResponseEntity
                        .ok(new AuthResponseDto(token, "Successfully authorized", usr.getId(), true));
            }
        } catch (AuthenticationException aEx) {
            throw new BadCredentialsException("Invalid usrName or password.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDto requestDto) {

        UsrEntity existingUsr = usrsRepository.findByUsrName(requestDto.getUserName());

        if (nonNull(existingUsr)) {
            log.info("IN register - usr with name {} already exists", requestDto.getUserName());
            return ResponseEntity
                    .badRequest()
                    .body(new RegisterResponseDto(null, "Usr with usrName already exists", false));
        }

        UsrEntity newUsr = UsrEntity.builder()
                .usrName(requestDto.getUserName())
                .playerName(requestDto.getPlayerName())
                .password(requestDto.getPasswordHash())
                .avatarData(requestDto.getAvatarData())
                .gamesCount(0L)
                .losesCount(0L)
                .winsCount(0L)
                .online(false)
                .build();

        UsrEntity savedUsr = usrsRepository.save(newUsr);
        log.info("Usr with name {} created successfully", requestDto.getUserName());

        return ResponseEntity
                .ok(new RegisterResponseDto(null, "Registration was successful", true));
    }
}
