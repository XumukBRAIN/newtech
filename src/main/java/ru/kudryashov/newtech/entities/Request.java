package ru.kudryashov.newtech.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table
public class Request {
    @Id
    private UUID id;
    private String number;
    private String title;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Статусы заявки
     */
    public interface Status {
        /**
         * Создана
         */
        Integer CODE_1 = 1;
        /**
         * Принята
         */
        Integer CODE_2 = 2;
        /**
         * Одобрена
         */
        Integer CODE_3 = 3;
        /**
         * Отклонена
         */
        Integer CODE_4 = 4;
    }
}
