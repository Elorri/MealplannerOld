package com.example.android.mealplannerold.model;

/**
 * Created by Elorri on 30/12/2015.
 */
public class Recipe extends Food {

    private String directions;


    public Recipe(int id, String name, String tags, String type, String directions) {
        super(id, name, tags, type);
        this.directions = directions;
    }

    public Recipe(String name, String tags, String directions) {
        super(name, tags);
        super.setType(FoodType.RECIPE.name());
        this.directions = directions;
    }


    public String getDirections() {
        return directions;
    }


    public String toString() {
        return "Recipe - id:" + this.getId() + " - name:" + this.getName() + " - quantity:" + this.getQuantity() +
                " - directions:" + this.getDirections() + " - tags:" + this.getTags();
    }

}
