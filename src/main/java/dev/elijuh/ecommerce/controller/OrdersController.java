package dev.elijuh.ecommerce.controller;

import dev.elijuh.ecommerce.dto.order.OrderCreateRequest;
import dev.elijuh.ecommerce.dto.order.OrderCreateResponse;
import dev.elijuh.ecommerce.entities.Order;
import dev.elijuh.ecommerce.entities.User;
import dev.elijuh.ecommerce.exception.UnauthorizedException;
import dev.elijuh.ecommerce.service.OrderService;
import dev.elijuh.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author elijuh
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrdersController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public List<Order> getOrders() {
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user)) {
            throw new UnauthorizedException();
        }

        return orderService.getOrders(user);
    }

    @GetMapping("/{userId}")
    public List<Order> getOrdersForUser(Long userId) {
        User user = userService.findById(userId);

        return orderService.getOrders(user);
    }

    @GetMapping("/active")
    public List<Order> getActiveOrders() {
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user)) {
            throw new UnauthorizedException();
        }

        return orderService.getActiveOrders(user);
    }

    @GetMapping("/active/{userId}")
    public List<Order> getActiveOrdersForUser(Long userId) {
        User user = userService.findById(userId);

        return orderService.getActiveOrders(user);
    }

    @PostMapping("/create")
    public OrderCreateResponse createOrder(@Valid @RequestBody OrderCreateRequest request) {
        return new OrderCreateResponse(orderService.createOrder(request));
    }
}
