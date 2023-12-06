package dev.elijuh.ecommerce.entities;

import dev.elijuh.ecommerce.entities.order.CartItem;
import dev.elijuh.ecommerce.entities.order.ShippingAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author elijuh
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varbinary(255) array")
    private List<CartItem> items;

    private ShippingAddress shippingAddress;
    private Boolean completed;

    @ManyToOne
    private User user;
}
