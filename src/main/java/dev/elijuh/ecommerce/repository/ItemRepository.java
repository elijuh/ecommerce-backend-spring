package dev.elijuh.ecommerce.repository;

import dev.elijuh.ecommerce.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author elijuh
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsBySku(String sku);

    Optional<Item> findBySku(String sku);
}
