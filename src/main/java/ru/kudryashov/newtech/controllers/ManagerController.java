package ru.kudryashov.newtech.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kudryashov.newtech.consts.SwaggerTag;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manager")
@Api(SwaggerTag.MANAGER_TAG)
public class ManagerController {
}
