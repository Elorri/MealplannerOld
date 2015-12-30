package com.example.android.mealplannerold.model;

public class Recipe extends Food {

    private String directions;


    public Recipe(Integer id, String name, String tags, String type, String directions) {
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
        return super.toString() +"|directions : " + this.getDirections();
    }

}
