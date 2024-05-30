package com.grocery.app.controller;

import com.grocery.app.entity.GroceryItems;
import com.grocery.app.model.request.OrderRequestDTO;
import com.grocery.app.service.GroceryItemService;
import com.grocery.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/orders")
@RequiredArgsConstructor
public class OrderController {

    private OrderService orderService;

    private GroceryItemService groceryItemService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<GroceryItems> findAvailableItems() {
        return groceryItemService.findAvailable();
    }

    @PostMapping("/book-items")
    @PreAuthorize("hasRole('USER')")
    public OrderRequestDTO bookItems(@RequestBody OrderRequestDTO requestDTO) {
        return orderService.bookItems(requestDTO);
    }

}
