package com.FoodDelivery.RestaurantService.Repository;

import com.FoodDelivery.RestaurantService.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}

