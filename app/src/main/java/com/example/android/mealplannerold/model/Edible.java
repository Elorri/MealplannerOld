package com.example.android.mealplannerold.model;

import com.example.android.mealplannerold.model.db.FoodDAO;
import com.example.android.mealplannerold.model.db.FoodQuantityDAO;
import com.example.android.mealplannerold.model.db.IngredientDAO;
import com.example.android.mealplannerold.model.db.RecipeDAO;

import java.util.Map;

public interface Edible {

    void setId(int id);

    int getId();

    String getName();

    String getTags();

    String getType();

    Double getQuantity();

    Double getEnergy();

    Double getCarbs();

    Double getProteins();

    Double getFats();

    String toString();


     String getChildrenType() ;
    Map<? extends Edible, Double> getChildrenFood();


    void setType(String type);

    void setChildrenFood(Map<? extends Edible, Double> childrenFood) ;

    Double sumChildrenQuantities(Map<? extends Edible, Double> list);

    void fetchAll(Double quantity_child, FoodDAO foodDAO, FoodQuantityDAO foodQuantityDAO, RecipeDAO recipeDAO, IngredientDAO ingredientDAO);

    String toStringFull(Double quantity_child, String message);
}
