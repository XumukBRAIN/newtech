package ru.kudryashov.newtech.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.kudryashov.newtech.entities.Manager;

import java.util.UUID;

/**
 * Репозиторий работы с менеджерами
 */
@Repository
public interface ManagerRepository extends R2dbcRepository<Manager, UUID> {
}
