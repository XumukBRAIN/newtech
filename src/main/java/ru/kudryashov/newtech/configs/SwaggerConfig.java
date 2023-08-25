package ru.kudryashov.newtech.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfo("NewTech Api",
                "Some custom description of API",
                "Spring Boot - 3.0.6",
                "https://github.com/XumukBRAIN/newtech#readme",
                new Contact("Ivan Kudryashov", "live:.cid.f25a1ae711d2391a", "kudryashov.id24@gmail.com"),
                "License of API", "https://swagger.io/docs/",
                Collections.emptyList());
    }
}
