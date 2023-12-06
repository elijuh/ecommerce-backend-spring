package dev.elijuh.ecommerce.service;

import dev.elijuh.ecommerce.dto.auth.AuthRequest;
import dev.elijuh.ecommerce.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * @author elijuh
 */

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager manager;
    private final UserService userService;
    private final JWTService jwtService;

    public String authenticate(AuthRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
            request.email(),
            request.password()
        ));

        User user = userService.findByEmail(request.email());

        return jwtService.generateToken(user);
    }
}
