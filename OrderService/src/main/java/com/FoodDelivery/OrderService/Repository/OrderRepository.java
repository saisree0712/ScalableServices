package com.FoodDelivery.OrderService.Repository;


import com.FoodDelivery.OrderService.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Orders, String> {
       List<Orders> findByUserId(String userId);
    List<Orders> findByRestaurantId(String restaurantId);

}


