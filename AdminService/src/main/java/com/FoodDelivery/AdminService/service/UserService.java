package com.FoodDelivery.AdminService.service;

import com.FoodDelivery.AdminService.Repository.UserRepository;
import com.FoodDelivery.AdminService.model.Orders;
import com.FoodDelivery.AdminService.model.Restaurant;
import com.FoodDelivery.AdminService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<Object> updateUser(User user) {
        try {
            userRepository.save(user);
            return ResponseEntity.status(200).body("Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update");
        }
    }

    public List<Orders> getAllOrdersDetails(String userId) {
        // Use the service name defined in docker-compose.yml
        String url = "http://order-service:5000/orders/" + userId;
        try {
            ResponseEntity<List<Orders>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Orders>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch orders", e);
        }
    }

    public List<Restaurant> getAllRestaurantDetails() {
        // Use the service name defined in docker-compose.yml
        String url = "http://restaurant-service:5000/restaurant";
        try {
            ResponseEntity<List<Restaurant>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Restaurant>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch restaurants", e);
        }
    }

    public ResponseEntity<Object> placeOrder(Orders orders) {
        // Use the service name defined in docker-compose.yml
        String url = "http://order-service:5000/orders";
        try {
            return restTemplate.postForEntity(url, orders, Object.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to place order", e);
        }
    }
}