package dev.elijuh.ecommerce.entities.order;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author elijuh
 */

public record ShippingAddress(@NotBlank String streetAddress,
                              @NotBlank String city,
                              @NotBlank String state,
                              @NotBlank String country,
                              @NotBlank String postalCode) implements Serializable {
}
