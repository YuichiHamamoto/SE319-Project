package com.t09.TrackYourFridge.repo;

import com.t09.TrackYourFridge.models.FoodItem;
import com.t09.TrackYourFridge.models.Fridge;
import com.t09.TrackYourFridge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface FridgeRepo extends JpaRepository<Fridge, Integer> {

    default List<Fridge> getString(String s) {

        List<Fridge> fridgeResult = new ArrayList<>();

        for (Fridge f : this.findAll()) {

            if (f.getFridgename().equals(s)) {
                fridgeResult.add(f);
            }

        }

        return fridgeResult;

    }

    default List<String> getFridgeByUser(String s) {

        List<String> fridges = new ArrayList<>();

        for (Fridge fridge : this.findAll()) {

            if (fridge.getUsername().equals(s)) {
                fridges.add(fridge.getFridgename());
            }

        }

        return fridges;

    }

}
