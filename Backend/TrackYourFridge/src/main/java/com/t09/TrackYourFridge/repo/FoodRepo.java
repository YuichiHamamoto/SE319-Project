package com.t09.TrackYourFridge.repo;

import com.t09.TrackYourFridge.models.FoodItem;
import com.t09.TrackYourFridge.models.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface FoodRepo extends JpaRepository<FoodItem, Integer> {

    default List<FoodItem> getFoodByFridge(String s) {

        List<FoodItem> foodItem = new ArrayList<>();

        for (FoodItem food : this.findAll()) {

            if (food.getFridgename().equals(s)) {
                foodItem.add(food);
            }

        }

        return foodItem;

    }

    default FoodItem findByFoodId(Integer id) {
        FoodItem tempResults = null;

        for (FoodItem food : this.findAll()) {
            if (food.getFoodID() == (id)) {
                tempResults = food;
            }
        }

        return tempResults;
    }

}
