package dev.elijuh.ecommerce.controller;

import dev.elijuh.ecommerce.dto.auth.AuthRequest;
import dev.elijuh.ecommerce.dto.auth.AuthResponse;
import dev.elijuh.ecommerce.dto.user.UserRegisterRequest;
import dev.elijuh.ecommerce.service.AuthService;
import dev.elijuh.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elijuh
 */

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(new AuthResponse(authService.authenticate(request)));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        return new ResponseEntity<>(new AuthResponse(userService.register(request)), HttpStatus.CREATED);
    }
}
