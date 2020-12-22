package com.t09.TrackYourFridge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "food")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodId")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer foodID;

    @Column(name = "foodname")
    @NotFound(action = NotFoundAction.IGNORE)
    private String foodname;

    @Column(name = "expiration")
    @NotFound(action = NotFoundAction.IGNORE)
    private String exp;

    @Column(name = "amount")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer amount;

    @Column(name = "unit")
    @NotFound(action = NotFoundAction.IGNORE)
    private String unit;

    @Column(name = "fridgename")
    @NotFound(action = NotFoundAction.IGNORE)
    private String fridgename;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fridge_id")
    private Fridge foodFridge;

    public FoodItem() { }

    public Integer getFoodID() {
        return foodID;
    }

    public void setFoodID(Integer foodID) {
        this.foodID = foodID;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getFridgename() {
        return fridgename;
    }

    public void setFridgename(String fridgename) {
        this.fridgename = fridgename;
    }

    public Fridge getFoodFridge() {
        return foodFridge;
    }

    public void setFoodFridge(Fridge foodFridge) {
        this.foodFridge = foodFridge;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
