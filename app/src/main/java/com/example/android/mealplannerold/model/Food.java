package com.example.android.mealplannerold.model;

import com.example.android.mealplannerold.model.db.FoodDAO;
import com.example.android.mealplannerold.model.db.FoodQuantityDAO;
import com.example.android.mealplannerold.model.db.IngredientDAO;
import com.example.android.mealplannerold.model.db.RecipeDAO;
import com.example.android.mealplannerold.model.Edible;

import java.util.Map;
import java.util.Set;


public class Food implements Edible {
    private int id;
    private String name;
    private String tags;
    private String type;

    public String getChildrenType() {
        return childrenType;
    }

    private String childrenType;

    public void setChildrenType(String parentType) {
        if (parentType.equals(com.example.android.mealplannerold.model.FoodType.DAY.name())) this.childrenType = com.example.android.mealplannerold.model.FoodType.MEAL.name();
        if (parentType.equals(com.example.android.mealplannerold.model.FoodType.MEAL.name())) this.childrenType = com.example.android.mealplannerold.model.FoodType.DISH.name();
        if (parentType.equals(com.example.android.mealplannerold.model.FoodType.DISH.name())) this.childrenType = com.example.android.mealplannerold.model.FoodType.RECIPE.name();
        if (parentType.equals(com.example.android.mealplannerold.model.FoodType.RECIPE.name()))
            this.childrenType = com.example.android.mealplannerold.model.FoodType.INGREDIENT.name();
    }

    private Map<? extends Edible, Double> childrenFood;


    public Food(int id, String name, String tags, String type) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.type = type;
        setChildrenType(type);
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
    public Map<? extends Edible, Double> getChildrenFood() {
        return childrenFood;
    }


    @Override
    public Double getQuantity() {
        if (childrenFood == null)
            return null;
        return sumChildrenQuantities(childrenFood);
    }

    @Override
    public Double getEnergy() {
        return getNutrientFromSumChildren(com.example.android.mealplannerold.model.Nutrient.ENERGY.name());
    }

    @Override
    public Double getCarbs() {
        return getNutrientFromSumChildren(com.example.android.mealplannerold.model.Nutrient.CARBS.name());
    }

    @Override
    public Double getProteins() {
        return getNutrientFromSumChildren(com.example.android.mealplannerold.model.Nutrient.PROTEINS.name());
    }

    @Override
    public Double getFats() {
        return getNutrientFromSumChildren(com.example.android.mealplannerold.model.Nutrient.FATS.name());
    }


    private Double getNutrientFromSumChildren(String name) {
        if (childrenFood == null) return null;
        Double nutrient = 0.0;
        for (Edible childFood : childrenFood.keySet()) {
            if (name.equals(com.example.android.mealplannerold.model.Nutrient.ENERGY.name())) nutrient = nutrient + childFood.getEnergy();
            if (name.equals(com.example.android.mealplannerold.model.Nutrient.PROTEINS.name()))
                nutrient = nutrient + childFood.getProteins();
            if (name.equals(com.example.android.mealplannerold.model.Nutrient.FATS.name())) nutrient = nutrient + childFood.getFats();
            if (name.equals(com.example.android.mealplannerold.model.Nutrient.CARBS.name())) nutrient = nutrient + childFood.getCarbs();
        }
        return nutrient;
    }


    public String toString() {
        return getType() + "|" + String.valueOf(getId()) + "|" + getName() + "|quantity:" + getQuantity() + "|energy:" + getEnergy()+ "|carbs:" + getCarbs()+ "|proteins:" + getProteins()+ "|fats:" + getFats() + "|" + getTags();
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
    public void setChildrenFood(Map<? extends Edible, Double> childrenFood) {
        this.childrenFood = childrenFood;
    }

    public Double sumChildrenQuantities(Map<? extends Edible, Double> childrenFood) {
        Double totalQuantity = 0.0;
        for (Double quantity : childrenFood.values()) {
            totalQuantity = totalQuantity + quantity;
        }
        return totalQuantity;
    }


    public void fetchAll(Double quantity, FoodDAO foodDAO, FoodQuantityDAO foodQuantityDAO, RecipeDAO recipeDAO, IngredientDAO ingredientDAO) {
        if (this instanceof com.example.android.mealplannerold.model.Ingredient) {
            return;
        }
        foodQuantityDAO.fetchChildrenFood(this, quantity, foodDAO, recipeDAO, ingredientDAO);
        for (Edible foodChildren : (Set<Edible>) this.getChildrenFood().keySet()) {
            Double quantity_child = this.getChildrenFood().get(foodChildren);
            foodChildren.fetchAll(quantity_child, foodDAO, foodQuantityDAO, recipeDAO, ingredientDAO);
        }
        return;
    }


    /**
     * Cette methode affiche les infos sur la "food" concernée puis les infos de ces enfants et enfants de ses enfants. Exple pour un dish on aura les infos sur le dish, les recipes dont il est constitué et les ingredients constituant les recipes.
     * Doit être appelé après un fetchAll pour que getEnergy, getProteins etc puissent s'afficher.
     *
     * @param message
     * @return
     */
    public String toStringFull(Double quantity, String message) {
        if (this instanceof com.example.android.mealplannerold.model.Ingredient) {
            message += "\n" + this.toString();
            return message;
        }
        message += "\n" + this.toString();
        for (Edible foodChildren : (Set<Edible>) this.getChildrenFood().keySet()) {
            Double quantity_child = this.getChildrenFood().get(foodChildren);
            message = foodChildren.toStringFull(quantity_child, message);
        }
        return message;
    }


}
