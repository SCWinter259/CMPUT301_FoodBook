package com.example.hoangtru_foodbook;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Food implements Serializable{
    String name;
    String description;
    String bestBefore;
    String location;
    Integer count;
    Integer cost;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getBestBefore() {
        return this.bestBefore;
    }

    public String getLocation() {
        return this.location;
    }

    public Integer getCount() {
        return this.count;
    }

    public Integer getCost() {
        return this.cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBestBefore(String bestBefore) {
        this.bestBefore = bestBefore;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
