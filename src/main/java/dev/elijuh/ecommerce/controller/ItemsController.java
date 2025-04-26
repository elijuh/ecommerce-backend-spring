package dev.elijuh.ecommerce.controller;

import dev.elijuh.ecommerce.dto.item.ItemCreateRequest;
import dev.elijuh.ecommerce.entities.Item;
import dev.elijuh.ecommerce.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author elijuh
 */

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@Validated
public class ItemsController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }

    @GetMapping("/sku/{sku}")
    public Item getItem(@PathVariable String sku) {
        return itemService.getItem(sku);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@Valid @RequestBody ItemCreateRequest body, UriComponentsBuilder uriBuilder) {
        Item item = itemService.createItem(body);

        URI uri = uriBuilder.path("/items/{id}").build(item.getId());

        return ResponseEntity.created(uri).body(item);
    }

    @DeleteMapping("/{id}")
    public Item deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
}
