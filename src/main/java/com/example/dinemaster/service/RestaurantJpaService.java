/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */
package com.example.dinemaster.service;

import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.RestaurantJpaRepository;
import com.example.dinemaster.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@Service
public class RestaurantJpaService implements RestaurantRepository {

	@Autowired
	private RestaurantJpaRepository restaurantJpaRepository;

	@Override
	public ArrayList<Restaurant> getRestaurants() {
		return (ArrayList<Restaurant>) restaurantJpaRepository.findAll();
	}

	@Override
	public Restaurant getRestaurantById(int restaurantId) {
		try {
			return restaurantJpaRepository.findById(restaurantId).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurantJpaRepository.save(restaurant);
		return restaurant;
	}

	@Override
	public Restaurant updateRestaurant(int restaurantId, Restaurant restaurant) {
		try {
			Restaurant newrestaurant = restaurantJpaRepository.findById(restaurantId).get();
			if (restaurant.getName() != null) {
				newrestaurant.setName(restaurant.getName());
			}
			if (restaurant.getAddress() != null) {
				newrestaurant.setAddress(restaurant.getAddress());
			}
			if (restaurant.getCuisineType() != null) {
				newrestaurant.setCuisineType(restaurant.getCuisineType());
			}
			if (restaurant.getRating() != 0) {
				newrestaurant.setRating(restaurant.getRating());
			}
			restaurantJpaRepository.save(newrestaurant);
			return newrestaurant;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		try {
			restaurantJpaRepository.deleteById(restaurantId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);
	}
}