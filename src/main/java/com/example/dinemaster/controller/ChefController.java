/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */
package com.example.dinemaster.controller;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.service.ChefJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ChefController {

    @Autowired
    public ChefJpaService chefService;

    @GetMapping("/restaurants/chefs")
    public ArrayList<Chef> getChefs() {
        return chefService.getChefs();
    }

    @GetMapping("/restaurants/chefs/{chefId}")
    public Chef getChefById(@PathVariable("chefId") int chefId) {
        return chefService.getChefById(chefId);
    }

    @PostMapping("/restaurants/chefs")
    public Chef addChef(@RequestBody Chef chef) {
        return chefService.addChef(chef);
    }

    @PutMapping("/restaurants/chefs/{chefId}")
    public Chef updateChef(@PathVariable("chefId") int chefId, @RequestBody Chef chef) {
        return chefService.updateChef(chefId, chef);
    }

    @DeleteMapping("/restaurants/chefs/{chefId}")
    public void deleteChef(@PathVariable("chefId") int chefId) {
        chefService.deleteChef(chefId);
    }

    @GetMapping("/chefs/{chefId}/restaurant")
    public Restaurant getRestaurantChef(@PathVariable("chefId") int chefId) {
        return chefService.getRestaurantChef(chefId);
    }
}