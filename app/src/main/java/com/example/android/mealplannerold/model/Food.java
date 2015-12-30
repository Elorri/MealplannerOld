package com.example.android.mealplannerold.model;

import java.util.Map;

/**
 * Created by Elorri on 30/12/2015.
 */
public class Food implements Edible {
    private int id;
    private String name;
    private String tags;
    private String type;

    private Map<Edible, Integer> childrenFood;

    private Map<Recipe, Integer> recipes;
    private Map<Ingredient, Integer> ingredients;


    public Food(int id, String name, String tags, String type) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.type = type;
    }

    public Food(String name, String tags, String type) {
        this.name = name;
        this.tags = tags;
        this.type = type;
    }

    public Food(String name, String tags) {
        this.name = name;
        this.tags = tags;
    }


    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTags() {
        return tags;
    }



    @Override
    public Map<Edible, Integer> getChildrenFood() {
        return childrenFood;
    }

    @Override
    public Map<Recipe, Integer> getRecipes() {
        return recipes;
    }
    @Override
    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public int getQuantity() {
        // TODO Implement this method
        return 0;
    }

    @Override
    public int getEnergy() {
        // TODO Implement this method
        return 0;
    }

    @Override
    public double getCarbs() {
        // TODO Implement this method
        return 0.0;
    }

    @Override
    public double getProteins() {
        // TODO Implement this method
        return 0.0;
    }

    @Override
    public double getFats() {
        // TODO Implement this method
        return 0.0;
    }

    public String toString() {
        return String.valueOf(id) + " - " + name + " - " + tags + " - " + type;
    }


    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public void setRecipes(Map recipes) {
        this.recipes = recipes;
    }
    @Override
    public void setIngredients(Map ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void setChildrenFood(Map childrenFood) {
        this.childrenFood = childrenFood;
    }

}
