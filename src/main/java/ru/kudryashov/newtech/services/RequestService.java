package ru.kudryashov.newtech.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.entities.Request;
import ru.kudryashov.newtech.repositories.RequestRepository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService {

    private final static String URL = "r2dbc:pool:postgres://localhost:5432/new_tech_db";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "Relhzijd";

    private final RequestRepository requestRepository;

    public Mono<Request> create(Request request) {
        return requestRepository.save(request.toBuilder()
                        .title(request.getTitle())
                        .number(generateNumber())
                        .status(Request.Status.CODE_1)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .doOnSuccess(req -> log.info("IN register - req: {} created", req));
    }

    public ResponseEntity<String> changeStatus(UUID id, Integer status) {
        requestRepository.findById(id)
                .doOnSuccess(this::change);

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
            throw new RuntimeException("Не удалось создать заказ!");
        }
        return number;
    }

    private void change(Request request) {
        Integer currentStatus = request.getStatus();
        if (Request.Status.CODE_1.equals(currentStatus)) {
            request.setStatus(Request.Status.CODE_2);
        } else if (Request.Status.CODE_2.equals(currentStatus) && StringUtils.isBlank(request.getTitle())) {
            request.setStatus(Request.Status.CODE_4);
        } else if (Request.Status.CODE_2.equals(currentStatus) && StringUtils.isNotBlank(request.getTitle())) {
            request.setStatus(Request.Status.CODE_3);
        } else {
            throw new RuntimeException("Нельзя сменить статус у данной заявки!");
        }
    }
}
