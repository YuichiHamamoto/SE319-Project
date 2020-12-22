package com.t09.TrackYourFridge.repo;

import com.t09.TrackYourFridge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {

    default List<User> getString(String s) {

        List<User> userResult = new ArrayList<>();

        for (User user : this.findAll()) {

            if (user.getUsername().equals(s)) {
                userResult.add(user);
            }

        }

        return userResult;

    }

}
