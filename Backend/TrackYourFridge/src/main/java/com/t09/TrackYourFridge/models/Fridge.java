package com.t09.TrackYourFridge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fridge")
public class Fridge {

    @Id
    @Column(name = "fridgename", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private String fridgename;

    @Column(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User fridgeUser;

    @OneToMany(mappedBy = "foodFridge")
    private Set<FoodItem> fridgeFood;

    public Fridge() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFridgename() {
        return fridgename;
    }

    public void setFridgename(String fridgename) {
        this.fridgename = fridgename;
    }

    public User getFridgeUser() {
        return fridgeUser;
    }

    public void setFridgeUser(User fridgeUser) {
        this.fridgeUser = fridgeUser;
    }

    public Set<FoodItem> getFridgeFood() {
        return fridgeFood;
    }

    public void setFridgeFood(Set<FoodItem> fridgeFood) {
        this.fridgeFood = fridgeFood;
    }

}
