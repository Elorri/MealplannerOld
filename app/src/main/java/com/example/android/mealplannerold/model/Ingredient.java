package com.example.android.mealplannerold.model;

public class Ingredient extends Food {
    private Double quantity;
    private Double energy;
    private Double carbs;
    private Double proteins;
    private Double fats;


    public Ingredient(Integer id, String name, String tags, String type, Double quantity, Double energy, Double carbs, Double proteins, Double fats) {
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
        this(name, tags, (double)quantity, (double)energy, carbs, proteins, fats);
    }

    public Ingredient(String name, String tags, Double quantity, Double energy, Double carbs, Double proteins, Double fats) {
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


    public Double getQuantity() {
        return quantity;
    }

    public Double getEnergy() {
        return energy;
    }

    public Double getCarbs() {
        return carbs;
    }

    public Double getProteins() {
        return proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void ajustToQuantity(Double quantity) {
        this.energy = (this.energy * quantity) / this.quantity;
        this.carbs = (this.carbs * quantity) / this.quantity;
        this.proteins = (this.proteins * quantity) / this.quantity;
        this.fats = (this.fats * quantity) / this.quantity;
        this.proteins = (this.proteins * quantity) / this.quantity;
        this.fats = (this.fats * quantity) / this.quantity;
        this.quantity = quantity;
    }


}
