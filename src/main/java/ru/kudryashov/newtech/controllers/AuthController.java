package ru.kudryashov.newtech.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.consts.SwaggerTag;
import ru.kudryashov.newtech.dto.AuthRequestDTO;
import ru.kudryashov.newtech.dto.AuthResponseDTO;
import ru.kudryashov.newtech.dto.UserDTO;
import ru.kudryashov.newtech.entities.User;
import ru.kudryashov.newtech.mappers.UserMapper;
import ru.kudryashov.newtech.security.CustomPrincipal;
import ru.kudryashov.newtech.security.SecurityService;
import ru.kudryashov.newtech.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Api(SwaggerTag.AUTH_TAG)
public class AuthController {
    private final SecurityService securityService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    @ApiOperation("Регистрация пользователя")
    public Mono<UserDTO> register(@RequestBody UserDTO userDTO) {
        User user = userMapper.map(userDTO);
        return userService.register(user).map(userMapper::map);
    }

    @PostMapping("/login")
    @ApiOperation("Аутентификация пользователя")
    public Mono<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return securityService.authenticate(authRequestDTO.getUsername(), authRequestDTO.getPassword())
                .flatMap(tokenDetails -> Mono.just(
                        AuthResponseDTO.builder()
                                .userId(tokenDetails.getUserId())
                                .token(tokenDetails.getToken())
                                .issuedAt(tokenDetails.getIssuedAt())
                                .expiresAt(tokenDetails.getExpiresAt())
                                .build())
                );
    }

    @GetMapping("/info")
    @ApiOperation("Получение информации о пользователе")
    public Mono<UserDTO> getUserInfo(Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        return userService.getUserById(principal.getId())
                .map(userMapper::map);
    }
}
