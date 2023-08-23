package ru.kudryashov.newtech.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.kudryashov.newtech.entities.Request;

import java.util.UUID;

/**
 * Репозиторий по работе с заявками
 */
@Repository
public interface RequestRepository extends R2dbcRepository<Request, UUID> {
}
