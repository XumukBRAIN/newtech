package ru.kudryashov.newtech.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.entities.User;
import ru.kudryashov.newtech.entities.Role;
import ru.kudryashov.newtech.repositories.UserRepository;
import ru.kudryashov.newtech.services.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сервис работы с пользователями
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserBaseService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * @see UserService#register(User)
     */
    @Override
    public Mono<User> register(User user) {
        return userRepository.save(user.toBuilder()
                        .password(passwordEncoder.encode(user.getPassword()))
                        .role(Role.USER)
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .doOnSuccess(u -> log.info("IN register - user: {} created", u));
    }

    /**
     * @see UserService#getUserById(UUID)
     */
    public Mono<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    /**
     * @see UserService#getUserByUsername(String)
     */
    public Mono<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
