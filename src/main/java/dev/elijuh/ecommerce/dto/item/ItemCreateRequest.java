package dev.elijuh.ecommerce.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * @author elijuh
 */

public record ItemCreateRequest(@NotBlank String sku,
                                @PositiveOrZero Integer stock,
                                @Positive Double price,
                                @NotBlank String title,
                                @NotBlank String description) {

}
