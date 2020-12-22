package com.t09.TrackYourFridge.controller;

import com.t09.TrackYourFridge.models.User;
import com.t09.TrackYourFridge.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RequestMapping("api/v1/user_data")
@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/user/add")
    public ResponseEntity create(@RequestBody User person) {
        // make sure to check whether the new person does not already exist
        return ResponseEntity.ok(userRepo.save(person));
    }

    //@RequestMapping(method = RequestMethod.POST, path = "/user/add")
    //public String createUser(User user) {

   //     userRepo.save(user);
   //     return "Saved: " + user.getUsername();

   // }

    @RequestMapping(method = RequestMethod.GET, path = "/user")
    public List<User> allUsers() {

        List<User> allUsers = userRepo.findAll();
        return allUsers;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{username}")
    public Optional<User> getUserById(@PathVariable("username") String username) {

        Optional<User> userById = userRepo.findById(username);
        return userById;

    }

}
