package ru.kudryashov.newtech.services;

import java.util.Collection;

/**
 * Интерфейс работы с топиками
 */
public interface TopicService {
    /**
     * Получить список топиков
     *
     * @return Список топиков
     */
    Collection<String> getTopics();
}
