package com.grocery.app.service;

import com.grocery.app.entity.GroceryItems;
import com.grocery.app.entity.Order;
import com.grocery.app.entity.OrderDetails;
import com.grocery.app.entity.User;
import com.grocery.app.model.request.OrderRequestDTO;
import com.grocery.app.repository.GroceryItemRepository;
import com.grocery.app.repository.OrderDetailsRepository;
import com.grocery.app.repository.OrderRepository;
import com.grocery.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private GroceryItemRepository groceryItemRepository;

    private UserRepository userRepository;

    private OrderDetailsRepository orderDetailsRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Transactional
    public OrderRequestDTO bookItems(OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(orderRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = new Order();
        order.setUser(user);

        List<OrderDetails> orderDetails = new ArrayList<>();
        for (OrderRequestDTO.OrderDetailDTO detailDTO : orderRequestDTO.getOrderDetails()) {
            GroceryItems item = groceryItemRepository.findById(detailDTO.getItemId())
                    .orElseThrow(() -> new IllegalArgumentException("Grocery item not found"));
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrder(order);
            orderDetail.setItem(item);
            orderDetail.setQuantity(detailDTO.getQuantity());
            orderDetailsRepository.save(orderDetail);
            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);
        save(order);
        return orderRequestDTO;
    }
}
