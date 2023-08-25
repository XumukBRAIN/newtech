package ru.kudryashov.newtech.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import ru.kudryashov.newtech.enums.Topic;
import ru.kudryashov.newtech.services.TopicService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
public class TopicCachedService implements TopicService {
    //кэш топиков
    private volatile Collection<String> cache;

    /**
     * @see TopicService#getTopics()
     */
    @Override
    public Collection<String> getTopics() {
        if (cache == null) {
            fillData();
        }
        return Collections.unmodifiableCollection(cache);
    }

    //заполнение кэша
    private void fillData() {
        Collection<String> topics = new CopyOnWriteArrayList<>(
                Arrays.stream(Topic.values()).map(Topic::getName).toList()
        );
        if (CollectionUtils.isNotEmpty(topics)) {
            cache = topics;
            log.info("Кэш топиков заполнен успешно.");
        }
    }
}

