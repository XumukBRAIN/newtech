package ru.kudryashov.newtech.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Топики
 */
@Getter
@AllArgsConstructor
public enum Topic {
    /**
     * Java Core
     */
    JAVA_CORE(1, "Java Core", Topic.TEMPLATE + "основам ЯП Java"),
    /**
     * Multithreading
     */
    MULTITHREADING(2, "Multithreading", Topic.TEMPLATE + "многопоточному программированию"),
    /**
     * Sql
     */
    SQL(3, "Sql", Topic.TEMPLATE + "SQL"),
    /**
     * Spring Core
     */
    SPRING_CORE(4, "Spring Core", Topic.TEMPLATE + "основам экосистемы Spring"),
    /**
     * Hibernate
     */
    HIBERNATE(5, "Hibernate", Topic.TEMPLATE + "основам Hibernate"),
    /**
     * Spring Data Jpa
     */
    SPRING_DATA_JPA(6, "Spring Data Jpa", Topic.TEMPLATE + "работе с Spring Data JPA");

    // Код
    private final Integer code;

    // Наименование
    private final String name;

    // Описание
    private final String description;

    // Шаблон описания топика
    private static final String TEMPLATE = "Раздел, посвященный вопросам по ";
}
