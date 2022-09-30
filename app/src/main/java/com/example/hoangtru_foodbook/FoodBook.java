package com.example.hoangtru_foodbook;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodBook implements Serializable {
    private ArrayList<Food> foodList = new ArrayList<>();
    private Integer position;
    private Integer totalCost = 0;
    private String locations[] = {"Pantry", "Freezer", "Fridge"};

    // a method to calculate the total cost of the food book
    private void totalCostCalculator(ArrayList<Food> foodList) {

    }

    // a method to add Food into the foodBook
    public void addFood(Food food) {
        this.foodList.add(food);
    }

    // a method to delete Food out of the foodBook
    public void deleteFood() {
        this.foodList.remove(this.position);
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Food getFood() {
        return this.foodList.get(this.position);
    }

    public String[] getLocations() {return this.locations;}

    public ArrayList<Food> getFoodList() {
        return this.foodList;
    }

    public Integer getTotalCost() {
        return this.totalCost;
    }
}
