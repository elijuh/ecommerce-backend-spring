package dev.elijuh.ecommerce.service;

import dev.elijuh.ecommerce.dto.order.OrderCreateRequest;
import dev.elijuh.ecommerce.entities.Order;
import dev.elijuh.ecommerce.entities.User;
import dev.elijuh.ecommerce.entities.order.CartItem;
import dev.elijuh.ecommerce.exception.UnauthorizedException;
import dev.elijuh.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elijuh
 */

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ItemService itemService;

    public List<Order> getOrders(User user) {
        return repository.findOrdersForUser(user);
    }

    public List<Order> getActiveOrders(User user) {
        return repository.findActiveOrdersForUser(user);
    }

    public Order createOrder(OrderCreateRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user == null) throw new UnauthorizedException();

        for (CartItem cartItem : request.cartItems()) {
            itemService.validateItem(cartItem.itemId());
        }

        Order order = Order.builder()
            .user(user)
            .items(request.cartItems())
            .shippingAddress(request.shippingAddress())
            .completed(false)
            .build();

        return repository.save(order);
    }
}
