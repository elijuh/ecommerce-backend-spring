package dev.elijuh.ecommerce.service;

import dev.elijuh.ecommerce.dto.item.ItemCreateRequest;
import dev.elijuh.ecommerce.entities.Item;
import dev.elijuh.ecommerce.exception.DuplicateEntryException;
import dev.elijuh.ecommerce.exception.ResourceNotFoundException;
import dev.elijuh.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elijuh
 */

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Item getItem(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("item", "no item with id '" + id + "' exists"));
    }

    public Item getItem(String sku) {
        return repository.findBySku(sku)
            .orElseThrow(() -> new ResourceNotFoundException("item", "no item with sku '" + sku + "' exists"));
    }

    public Item createItem(ItemCreateRequest body) {
        if (repository.existsBySku(body.sku())) throw new DuplicateEntryException("sku", body.sku());

        return repository.save(Item.builder()
                .sku(body.sku())
                .price(body.price())
                .stock(body.stock())
                .title(body.title())
                .description(body.description())
                .build()
        );
    }

    public Item deleteItem(Long id) {
        Item item = getItem(id);
        repository.deleteById(id);
        return item;
    }

    public void validateItem(Long id) {
        if (id == null || !repository.existsById(id)) {
            throw new ResourceNotFoundException("item", "no item with id '" + id + "' exists");
        }
    }
}
