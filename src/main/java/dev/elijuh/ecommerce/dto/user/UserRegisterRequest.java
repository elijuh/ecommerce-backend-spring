package dev.elijuh.ecommerce.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author elijuh
 */

public record UserRegisterRequest(@Email @NotBlank String email,
                                  @NotBlank String firstName,
                                  @NotBlank String lastName,
                                  @NotBlank String password) {
}
