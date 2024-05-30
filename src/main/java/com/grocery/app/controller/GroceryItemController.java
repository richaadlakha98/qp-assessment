package com.grocery.app.controller;

import com.grocery.app.entity.GroceryItems;
import com.grocery.app.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/grocery-items")
@RequiredArgsConstructor
public class GroceryItemController {

    private GroceryItemService groceryItemService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<GroceryItems> findAll() {
        return groceryItemService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<GroceryItems> add(@RequestBody List<GroceryItems> items) {
        return groceryItemService.add(items);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<GroceryItems> findById(@PathVariable Long id) {
        return groceryItemService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        groceryItemService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GroceryItems update(@PathVariable Long id, @RequestBody GroceryItems groceryItems) {
        return groceryItemService.update(groceryItems);
    }

    @PutMapping("/{id}/inventory")
    @PreAuthorize("hasRole('ADMIN')")
    public GroceryItems updateInventory(@PathVariable Long id, @RequestParam int stock) {
        return groceryItemService.updateInventory(id, stock);
    }
}
