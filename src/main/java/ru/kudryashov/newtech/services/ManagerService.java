package ru.kudryashov.newtech.services;

import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.dto.ManagerDTO;
import ru.kudryashov.newtech.entities.Manager;

import java.util.UUID;

/**
 * Интерфейс работы с менеджерами
 */
public interface ManagerService {
    /**
     * Создать менеджера
     *
     * @param manager Данные менеджера
     * @return Менеджер
     */
    Mono<ManagerDTO> register(Manager manager);

    /**
     * Получить менеджера по идентификатору
     *
     * @param id Идентификатор
     * @return Менеджер
     */
    Mono<ManagerDTO> getById(UUID id);
}
