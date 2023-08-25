package ru.kudryashov.newtech.services;

import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.entities.User;

import java.util.UUID;

/**
 * Интерфейс работы с пользователями
 */
public interface UserService {
    /**
     * Зарегистрировать пользователя
     *
     * @param user Данные пользователя
     * @return Зарегистрированный пользователь
     */
    Mono<User> register(User user);

    /**
     * Получить пользователя по идентификатору
     *
     * @param id Идентификатор
     * @return Пользователь
     */
    Mono<User> getUserById(UUID id);

    /**
     * Получить пользователя по имени
     *
     * @param username Имя
     * @return Пользователь
     */
    Mono<User> getUserByUsername(String username);
}
