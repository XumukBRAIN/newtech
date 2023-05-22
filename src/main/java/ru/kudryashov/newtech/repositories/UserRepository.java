package ru.kudryashov.newtech.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.entities.User;

import java.util.UUID;

/**
 * Репозиторий по работе с пользователями
 */
@Repository
public interface UserRepository extends R2dbcRepository<User, UUID> {
    /**
     * Найти пользователя по username
     *
     * @param username Имя пользователя
     * @return Пользователь
     */
    Mono<User> findByUsername(String username);
}
