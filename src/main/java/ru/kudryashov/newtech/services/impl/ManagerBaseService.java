package ru.kudryashov.newtech.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.dto.ManagerDTO;
import ru.kudryashov.newtech.entities.Manager;
import ru.kudryashov.newtech.mappers.ManagerMapper;
import ru.kudryashov.newtech.repositories.ManagerRepository;
import ru.kudryashov.newtech.services.ManagerService;

import java.util.UUID;

/**
 * Сервис работы с менеджерами
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerBaseService implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    /**
     * @see ManagerService#register(Manager)
     */
    @Override
    public Mono<ManagerDTO> register(Manager manager) {
        return managerRepository.save(manager)
                .map(managerMapper::map)
                .doOnSuccess(x -> log.info("Создание менеджера прошло успешно"));
    }

    /**
     * @see ManagerService#getById(UUID)
     */
    @Override
    public Mono<ManagerDTO> getById(UUID id) {
        return managerRepository.findById(id)
                .map(managerMapper::map);
    }
}
