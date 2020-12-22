package com.example.track_your_fridge.Logic.InTheFridge;

public class Food {
    private String name;
    private int amount;
    private Unit unit;

    public Food(String name, int amount, Unit unit){
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getAmount(){
        return amount;
    }

    public String getName(){
        return name;
    }

    public Unit getUnit(){
        return unit;
    }
}
