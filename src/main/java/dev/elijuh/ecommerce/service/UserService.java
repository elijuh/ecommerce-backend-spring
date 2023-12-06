package dev.elijuh.ecommerce.service;

import dev.elijuh.ecommerce.dto.user.UserRegisterRequest;
import dev.elijuh.ecommerce.entities.User;
import dev.elijuh.ecommerce.entities.user.Role;
import dev.elijuh.ecommerce.exception.DuplicateEntryException;
import dev.elijuh.ecommerce.exception.ResourceNotFoundException;
import dev.elijuh.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author elijuh
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final JWTService jwtService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Attempts to find a {@link User} in the repository
     * with an email value matching the provided argument.
     *
     * @param email the email to query the user off of.
     * @return the {@link User} entity from database.
     * @throws ResourceNotFoundException if no user is found
     * with an email matching the provided argument.
     */
    public User findByEmail(String email) throws ResourceNotFoundException {
        return repository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("user", "no user found with email " + email));
    }

    /**
     * Register a new user entity from an HTTP request
     *
     * @param request the request body wrapper with information for the new user.
     * @return A generated token for the user's authentication.
     */
    public String register(UserRegisterRequest request) {
        if (existsByEmail(request.email())) {
            throw new DuplicateEntryException("email", request.email().toLowerCase());
        }

        User user = User.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email().toLowerCase())
            .password(passwordEncoder.encode(request.password()))
            .role(Role.USER)
            .build();

        repository.save(user);

        return jwtService.generateToken(user);
    }

    /**
     * Checks if a {@link User} in the repository
     * already exists with the given email address.
     *
     * @param email the email to check for.
     * @return <code>true</code> if a {@link User} was found, otherwise <code>false</code>.
     */
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email.toLowerCase());
    }

    /**
     * Attempts to find a {@link User} in the repository
     * with an id matching the provided argument.
     *
     * @param id the id to query the user off of.
     * @return the {@link User} entity from database.
     * @throws ResourceNotFoundException if no user is found
     * with an id matching the provided argument.
     */
    public User findById(Long id) throws ResourceNotFoundException {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("user", "no user found with id " + id));
    }
}
