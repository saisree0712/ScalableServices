package com.FoodDelivery.OrderService.service;

import com.FoodDelivery.OrderService.Repository.OrderRepository;
import com.FoodDelivery.OrderService.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
@Autowired
    private   OrderRepository orderRepository;


    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }


    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }
}
