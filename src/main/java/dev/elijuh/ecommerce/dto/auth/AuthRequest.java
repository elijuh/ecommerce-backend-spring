package dev.elijuh.ecommerce.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author elijuh
 */

public record AuthRequest(@Email String email, @NotBlank String password) {
}
