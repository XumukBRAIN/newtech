package ru.kudryashov.newtech.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.kudryashov.newtech.entities.Note;

import java.util.UUID;

/**
 * Репозиторий работы с заметками
 */
@Repository
public interface NoteRepository extends R2dbcRepository<Note, UUID> {
}
