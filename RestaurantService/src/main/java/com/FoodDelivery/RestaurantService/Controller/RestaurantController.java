package com.FoodDelivery.RestaurantService.Controller;


import com.FoodDelivery.RestaurantService.Repository.RestaurantRepository;
import com.FoodDelivery.RestaurantService.model.Orders;
import com.FoodDelivery.RestaurantService.model.Restaurant;
import com.FoodDelivery.RestaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantService restaurantService;


    @PostMapping("/restaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("/restaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/restaurant/menu/{restaurantId}")
    public List<Restaurant.MenuItem> getAllItemsInRestaurant(@PathVariable String restaurantId) {
        return restaurantRepository.findById(restaurantId).orElse(new Restaurant()).getMenu();
    }

    @GetMapping("/restaurant/orders/{restaurantId}")
    public List<Orders> fetchOrdersByRestaurant(@PathVariable String restaurantId) {
        return restaurantService.fetchOrdersByRestaurant(restaurantId);
    }

    @GetMapping("/restaurant")
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @PutMapping("/restaurant/orders")
    public String updateOrdersStatus(@RequestBody Orders orders) {
        return restaurantService.updateOrdersStatus(orders);
    }

    }


