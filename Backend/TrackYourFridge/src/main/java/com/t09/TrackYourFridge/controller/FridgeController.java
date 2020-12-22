package com.t09.TrackYourFridge.controller;

import com.t09.TrackYourFridge.models.Fridge;
import com.t09.TrackYourFridge.models.User;
import com.t09.TrackYourFridge.repo.FridgeRepo;
import com.t09.TrackYourFridge.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RequestMapping("api/v1/fridge")
@RestController
public class FridgeController {

    @Autowired
    private FridgeRepo fridgeRepo;

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/fridge/add")
    public ResponseEntity createFridge(@RequestBody Fridge fridge) {

        String userSearch = fridge.getUsername();

        List<User> foundUser = userRepo.getString(userSearch);

        fridge.setFridgeUser(foundUser.get(0));

        return ResponseEntity.ok(fridgeRepo.save(fridge));

    }

    @RequestMapping(method = RequestMethod.GET, path = "/fridge")
    public List<Fridge> allFridges() {

        List<Fridge> allFridges = fridgeRepo.findAll();
        return allFridges;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/fridge/{fridgeId}")
    public Optional<Fridge> getUserById(@PathVariable("fridgeId") Integer fridgeId) {

        Optional<Fridge> fridgeById = fridgeRepo.findById(fridgeId);
        return fridgeById;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/fridge/user/{username}")
    public List<String> getUserById(@PathVariable("username") String user) {

        List<String> fridgeNames = fridgeRepo.getFridgeByUser(user);

        return fridgeNames;

    }

}
