package com.FoodDelivery.OrderService.Controller;

import com.FoodDelivery.OrderService.Repository.OrderRepository;
import com.FoodDelivery.OrderService.model.Orders;
import com.FoodDelivery.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController

public class OrderController {
    @Autowired
     OrderService orderService;


    @Autowired
     OrderRepository orderRepository;

    @Autowired
     RestTemplate restTemplate;



    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<Orders>> getOrderByUser(@PathVariable String userId) {
        return ResponseEntity.ok(orderRepository.findByUserId(userId));
    }

    @GetMapping("/orders/restaurant/{restaurantId}")
    public ResponseEntity<List<Orders>> getOrderByRestaurant(@PathVariable String restaurantId) {
        return ResponseEntity.ok(orderRepository.findByRestaurantId(restaurantId));
    }

    @PostMapping("/orders")
    public ResponseEntity<Object> createOrder(@RequestBody Orders order) {

        // Save the order
        order.setStatus("Order Placed");
        order.setOrderedAt(System.currentTimeMillis());
        return ResponseEntity.ok(orderService.saveOrder(order));

    }

    @PutMapping("/orders/update")
    public ResponseEntity<Object> updateOrder(@RequestBody Orders order) {

        // Save the order
        return ResponseEntity.ok(orderService.saveOrder(order));

    }

}


