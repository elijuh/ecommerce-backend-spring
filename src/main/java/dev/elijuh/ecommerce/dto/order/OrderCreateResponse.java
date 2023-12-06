package dev.elijuh.ecommerce.dto.order;

import dev.elijuh.ecommerce.entities.Order;
import dev.elijuh.ecommerce.entities.order.CartItem;
import dev.elijuh.ecommerce.entities.order.ShippingAddress;
import lombok.Getter;

import java.util.List;

/**
 * @author elijuh
 */

@Getter
public class OrderCreateResponse {
    private final Long id, userId;
    private final ShippingAddress shippingAddress;
    private final List<CartItem> items;
    private final Boolean completed;

    public OrderCreateResponse(Order order) {
        this.id = order.getId();
        this.userId = order.getUser().getId();
        this.shippingAddress = order.getShippingAddress();
        this.items = order.getItems();
        this.completed = order.getCompleted();
    }
}
