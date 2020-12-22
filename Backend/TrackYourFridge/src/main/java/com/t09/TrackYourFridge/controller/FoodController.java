package com.t09.TrackYourFridge.controller;

import com.t09.TrackYourFridge.models.FoodItem;
import com.t09.TrackYourFridge.models.Fridge;
import com.t09.TrackYourFridge.repo.FoodRepo;
import com.t09.TrackYourFridge.repo.FridgeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@RequestMapping("api/v1/food")
@RestController
public class FoodController {

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private FridgeRepo fridgeRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/food/add")
    public ResponseEntity createFood(@RequestBody FoodItem food) {

        String fridgeSearch = food.getFridgename();

        List<Fridge> foundFridge = fridgeRepo.getString(fridgeSearch);

        food.setFoodFridge(foundFridge.get(0));

        return ResponseEntity.ok(foodRepo.save(food));

    }

    @RequestMapping(method = RequestMethod.GET, path = "/food")
    public List<FoodItem> allFoods() {

        List<FoodItem> allFoods = foodRepo.findAll();
        return allFoods;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/food/{foodId}")
    public Optional<FoodItem> getFoodById(@PathVariable("foodId") Integer foodId) {

        Optional<FoodItem> foodById = foodRepo.findById(foodId);
        return foodById;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/food/fridge/{fridgename}")
    public List<FoodItem> getFoodByFridge(@PathVariable("fridgename") String fridge) {

        List<FoodItem> foodById = foodRepo.getFoodByFridge(fridge);
        return foodById;

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/food/delete/{foodId}")
    public int deleteFood(@PathVariable("foodId") Integer id) {
        FoodItem item = foodRepo.findByFoodId(id);

        if (item == null) {
            return 1;
        }

        foodRepo.delete(item);
        return 1;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/food/update/{foodId}")
    public int updateFood(@PathVariable("postId") Integer id, @RequestParam(required = false, name = "expiration")String expiration,
                               @RequestParam(required = false, name = "amount")Integer amount,
                               @RequestParam(required = false, name = "unit")String unit) {

        FoodItem foods = foodRepo.findByFoodId(id);

        if (foods == null) {
            return 0;
        }
        else {

            FoodItem updateFood = foods;

            if (expiration != null) {
                updateFood.setExp(expiration);
            }
            if (amount != null) {
                updateFood.setAmount(amount);
            }
            if (unit != null) {
                updateFood.setUnit(unit);
            }

            foodRepo.save(updateFood);

        }
        return 1;
    }

}
