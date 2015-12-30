package com.example.android.mealplannerold.model;

/**
 * Created by Elorri on 30/12/2015.
 */
public class Ingredient extends Food{
    private int quantity;
    private int energy;
    private double carbs;
    private double proteins;
    private double fats;


    public Ingredient(int id, String name, String tags,String type, int quantity, int energy, double carbs, double proteins, double fats) {
        super(id, name, tags, type);
        this.quantity = quantity;
        this.energy = energy;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
        this.proteins = proteins;
        this.fats = fats;
    }
    public Ingredient(String name, String tags, int quantity, int energy, double carbs, double proteins, double fats) {
        super(name, tags);
        super.setType(FoodType.INGREDIENT.name());
        this.quantity = quantity;
        this.energy = energy;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
        this.proteins = proteins;
        this.fats = fats;
    }


    public int getQuantity() { return quantity;  }

    public int getEnergy() {
        return energy;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }


    public String toString() {
        return "Ingredient - id:" + this.getId() + " - name:" + this.getName() + " - quantity:" + this.getQuantity() + " - energy:" + this.getEnergy() + " - carbs:" + this.getCarbs() + " - proteins:" + this.getProteins() + " - fats:" + this.getFats();
    }
}
