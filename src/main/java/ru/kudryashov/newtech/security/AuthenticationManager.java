package ru.kudryashov.newtech.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.entities.User;
import ru.kudryashov.newtech.exceptions.UnauthorizedException;
import ru.kudryashov.newtech.services.UserService;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final UserService userService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        return userService.getUserById(principal.getId())
                .filter(User::isEnabled)
                .switchIfEmpty(Mono.error(new UnauthorizedException("the user is disabled")))
                .map(user -> authentication);
    }
}
