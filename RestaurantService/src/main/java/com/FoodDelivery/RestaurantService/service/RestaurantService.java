package com.FoodDelivery.RestaurantService.service;

import com.FoodDelivery.RestaurantService.Repository.RestaurantRepository;
import com.FoodDelivery.RestaurantService.model.Orders;
import com.FoodDelivery.RestaurantService.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Restaurant> fetchAllRestaurant() {
        String url = "http://restaurant-service:5000/restaurants"; // Updated URL
        try {
            ResponseEntity<List<Restaurant>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Restaurant>>() {
                    });
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch restaurants", e);
        }
    }

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Orders> fetchAllOrders(String restaurantId) {
        if (getRestaurantById(restaurantId) == null) {
            return null;
        }
        String url = "http://order-service:5000/order/restaurant/" + restaurantId; // Updated URL
        try {
            ResponseEntity<List<Orders>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Orders>>() {
                    });
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch orders", e);
        }
    }

    public List<Orders> fetchOrdersByRestaurant(String restaurantId) {
        String url = "http://order-service:5000/orders/restaurant/" + restaurantId; // Updated URL
        try {
            ResponseEntity<List<Orders>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Orders>>() {
                    });
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch orders", e);
        }
    }

    public String updateOrdersStatus(Orders orders) {
        String url = "http://order-service:5000/orders/update"; // Updated URL
        try {
            restTemplate.put(url, orders);
            return "Updated Successfully";
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to update order status", e);
        }
    }
}