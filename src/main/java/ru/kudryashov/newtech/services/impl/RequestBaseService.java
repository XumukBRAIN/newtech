package ru.kudryashov.newtech.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.dto.RequestDTO;
import ru.kudryashov.newtech.entities.Request;
import ru.kudryashov.newtech.mappers.RequestMapper;
import ru.kudryashov.newtech.repositories.RequestRepository;
import ru.kudryashov.newtech.services.RequestService;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * Сервис работы с заявками
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestBaseService implements RequestService {

    private final static String URL = "r2dbc:pool:postgres://localhost:5432/new_tech_db";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "Relhzijd";

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    /**
     * @see RequestService#create(Request)
     */
    @Override
    public Mono<RequestDTO> create(Request request) {
        return requestRepository.save(request.toBuilder()
                        .title(request.getTitle())
                        .number(generateNumber())
                        .status(Request.Status.CODE_1)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .map(requestMapper::map)
                .doOnSuccess(req -> log.info("IN register - req: {} created", req));
    }

    /**
     * @see RequestService#changeStatus(UUID, Integer)
     */
    @Override
    public ResponseEntity<String> changeStatus(UUID id, Integer status) {
        requestRepository.findById(id)
                .switchIfEmpty(
                        //todo Изменить на кастомное исключение
                        Mono.error(new RuntimeException("Заявка с идентификатором " + id + " не найдена"))
                )
                .doOnSuccess(req -> {
                    req.setStatus(status);
                    requestRepository.save(req);
                });

        return ResponseEntity.ok("Смена статуса прошла успешно");
    }

    private String generateNumber() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/");
        String number = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select nextval('request_number_seq')");

            if (rs.next()) {
                number = dateFormat.format(new Date()) +
                        StringUtils.leftPad(String.valueOf(rs.getLong("nextval")), 4, "0");
            }

            connection.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("При генерации номера заявки произошла ошибка!");
        }
        return number;
    }
}
