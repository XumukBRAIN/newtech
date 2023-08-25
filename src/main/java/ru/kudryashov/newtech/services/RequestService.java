package ru.kudryashov.newtech.services;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.dto.RequestDTO;
import ru.kudryashov.newtech.entities.Request;

import java.util.UUID;

/**
 * Интерфейс работы с заявками
 */
public interface RequestService {
    /**
     * Создать заявку
     *
     * @param request Данные заявки
     * @return Созданная заявка
     */
    Mono<RequestDTO> create(Request request);

    /**
     * Поменять статус заявки
     *
     * @param id Идентификатор заявки
     * @param status Статус
     * @return ResponseEntity
     */
    ResponseEntity<String> changeStatus(UUID id, Integer status);
}
