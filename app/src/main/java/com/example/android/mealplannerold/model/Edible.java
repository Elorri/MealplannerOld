package com.example.android.mealplannerold.model;

import java.util.Map;

/**
 * Created by Elorri on 30/12/2015.
 */
public interface Edible<F extends Food> {

    void setId(int id);

    int getId();

    String getName();

    String getTags();

    String getType();


    int getQuantity();

    int getEnergy();

    double getCarbs();

    double getProteins();

    double getFats();

    String toString();



    Map<Edible, Integer> getChildrenFood();

    Map<Recipe, Integer> getRecipes();

    Map<Ingredient, Integer> getIngredients();


    void setType(String type);

    void setRecipes(Map<Recipe, Integer> recipes);

    void setIngredients(Map<Ingredient, Integer> ingredients);



    void setChildrenFood(Map<Edible, Integer> childrenFood) ;
}
