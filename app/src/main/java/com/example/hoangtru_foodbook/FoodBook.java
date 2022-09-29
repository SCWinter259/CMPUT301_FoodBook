package com.example.hoangtru_foodbook;

import java.util.ArrayList;

public class FoodBook {
    ArrayList<Food> foodList = new ArrayList<>();
    Integer totalCost = 0;

    // a method to calculate the total cost of the food book
    private void totalCostCalculator(ArrayList<Food> foodList) {

    }

    // a method to add Food into the foodBook
    public void addFood() {

    }

    // a method to delete Food out of the foodBook
    public void deleteFood() {

    }

    public ArrayList<Food> getFoodList() {
        return this.foodList;
    }

    public Integer getTotalCost() {
        return this.totalCost;
    }
}
