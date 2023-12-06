package dev.elijuh.ecommerce.entities.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * @author elijuh
 */
public record CartItem(@NotNull Long itemId, @NotNull @Positive Integer quantity) implements Serializable {
}
