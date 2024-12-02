/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */
package com.example.dinemaster.service;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.ChefJpaRepository;
import com.example.dinemaster.repository.ChefRepository;
import com.example.dinemaster.service.RestaurantJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class ChefJpaService implements ChefRepository {

	@Autowired
	private ChefJpaRepository chefJpaRepository;

	@Autowired
	private RestaurantJpaService restaurantJpaService;

	@Override
	public ArrayList<Chef> getChefs() {
		return (ArrayList<Chef>) chefJpaRepository.findAll();
	}

	@Override
	public Chef getChefById(int chefId) {
		try {
			return chefJpaRepository.findById(chefId).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Chef addChef(Chef chef) {
		Restaurant restaurant = chef.getRestaurant();
		int restaurantId = restaurant.getId();
		try {
			Restaurant newrestaurant = restaurantJpaService.getRestaurantById(restaurantId);
			chef.setRestaurant(newrestaurant);
			chefJpaRepository.save(chef);
			return chef;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Chef updateChef(int chefId, Chef chef) {
		try {
			Chef newchef = chefJpaRepository.findById(chefId).get();
			if (chef.getFirstName() != null) {
				newchef.setFirstName(chef.getFirstName());
			}
			if (chef.getLastName() != null) {
				newchef.setLastName(chef.getLastName());
			}
			if (chef.getExpertise() != null) {
				newchef.setExpertise(chef.getExpertise());
			}
			if (chef.getExperienceYears() != 0) {
				newchef.setExperienceYears(chef.getExperienceYears());
			}
			if (chef.getRestaurant() != null) {
				int restaurantId = chef.getRestaurant().getId();
                Restaurant restaurant = restaurantJpaService.getRestaurantById(restaurantId);
                newchef.setRestaurant(restaurant);
			}
			chefJpaRepository.save(newchef);
			return newchef;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteChef(int chefId) {
		try {
			chefJpaRepository.deleteById(chefId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);
	}

	@Override
	public Restaurant getRestaurantChef(int chefId) {
		try {
			Chef chef = chefJpaRepository.findById(chefId).get();
			Restaurant restaurant = chef.getRestaurant();
			return restaurant;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}