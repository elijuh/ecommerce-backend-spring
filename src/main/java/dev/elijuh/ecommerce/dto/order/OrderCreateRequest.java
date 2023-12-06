package dev.elijuh.ecommerce.dto.order;

import dev.elijuh.ecommerce.entities.order.CartItem;
import dev.elijuh.ecommerce.entities.order.ShippingAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @author elijuh
 */

public record OrderCreateRequest(@Valid @NotEmpty List<CartItem> cartItems,
                                 @Valid @NotNull ShippingAddress shippingAddress) {

}
