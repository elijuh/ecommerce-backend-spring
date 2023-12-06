package dev.elijuh.ecommerce.repository;

import dev.elijuh.ecommerce.entities.Order;
import dev.elijuh.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author elijuh
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user = :user AND o.completed = false")
    List<Order> findActiveOrdersForUser(@Param("user") User user);

    @Query("SELECT o FROM Order o WHERE o.user = :user")
    List<Order> findOrdersForUser(@Param("user") User user);
}
