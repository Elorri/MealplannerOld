package com.example.android.mealplannerold.model.db;

import android.content.Context;
import android.database.Cursor;

import com.example.android.mealplannerold.model.Edible;
import com.example.android.mealplannerold.model.Food;
import com.example.android.mealplannerold.model.FoodType;
import com.example.android.mealplannerold.model.Ingredient;
import com.example.android.mealplannerold.model.Recipe;
import com.example.android.mealplannerold.model.db.DAO;
import com.example.android.mealplannerold.model.db.FoodDAO;
import com.example.android.mealplannerold.model.db.RecipeDAO;

import java.util.HashMap;
import java.util.Map;

public class FoodQuantityDAO extends DAO {

    public FoodQuantityDAO(Context context) {
        super(context);
    }

    private static String TABLE = "FOOD_QUANTITY";

    public static String QUERY_CREATION =
            "CREATE TABLE " + TABLE + "(" + "ID_FOOD_PARENT INTEGER, " + "ID_FOOD_CHILDREN INTEGER, " +
                    "QUANTITY INTEGER, " + "PRIMARY KEY(ID_FOOD_PARENT, ID_FOOD_CHILDREN) " +
                    "FOREIGN KEY (ID_FOOD_PARENT) REFERENCES FOOD(ID)  " + "FOREIGN KEY (ID_FOOD_CHILDREN) REFERENCES FOOD(ID)  " +
                    "CHECK (QUANTITY>0))";


    public static String QUERY_INSERT =
            "INSERT INTO " + TABLE + " (ID_FOOD_PARENT, ID_FOOD_CHILDREN, QUANTITY) " + "VALUES (?, ?, ?)";

    private static String QUERY_UPDATE =
            "UPDATE " + TABLE + " SET QUANTITY=?" + "WHERE ID_FOOD_PARENT=? AND ID_FOOD_CHILDREN=?";

    private static String QUERY_DELETE = "DELETE FROM " + TABLE + " WHERE ID_FOOD_PARENT=?";

    private static String QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT =
            "SELECT ID_FOOD_CHILDREN, QUANTITY FROM " + TABLE + " t WHERE t.ID_FOOD_PARENT = ?";

    private static String QUERY_SEARCH_ALL = "SELECT * FROM " + TABLE;


    public void fetchChildrenFood(Edible parentFood, FoodDAO foodDAO, RecipeDAO recipeDAO, com.example.android.mealplannerold.model.db.IngredientDAO ingredientDAO) {
        fetchChildrenFood(parentFood, null, foodDAO, recipeDAO, ingredientDAO);
    }


    public void fetchChildrenFood(Edible parentFood, Double quantity, FoodDAO foodDAO, RecipeDAO recipeDAO, com.example.android.mealplannerold.model.db.IngredientDAO ingredientDAO) {
        parentFood.setChildrenFood(getChildrenFood(parentFood, quantity, foodDAO, recipeDAO, ingredientDAO));
    }


    private Map<? extends Edible, Double> getChildrenFood(Edible parentFood, Double quantity, FoodDAO foodDAO, RecipeDAO recipeDAO, com.example.android.mealplannerold.model.db.IngredientDAO ingredientDAO) {
        Map<? extends Edible, Double> list;
        Cursor results = db.rawQuery(QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT, new String[]{
                String.valueOf(parentFood.getId())});
        list = extract(results, parentFood.getChildrenType(), foodDAO, recipeDAO, ingredientDAO);
        if (quantity == null)
            return list;
        else {
            Double oldTotalQuantity = parentFood.sumChildrenQuantities(list);
            list = adjustQuantities(list, oldTotalQuantity, quantity);
            return list;
        }
    }


    private Map<Edible, Double> extract(Cursor results, String childrenType, FoodDAO foodDAO, RecipeDAO recipeDAO, com.example.android.mealplannerold.model.db.IngredientDAO ingredientDAO) {
        Map<Edible, Double> list = new HashMap<Edible, Double>();
        while (results.moveToNext()) {
            Integer id = results.getInt(0);
            Double quantity = results.getDouble(1);
            if (childrenType.equals(FoodType.RECIPE.name())) {
                Recipe recipe = recipeDAO.getById(id);
                list.put(recipe, quantity);
            } else {
                if (childrenType.equals(FoodType.INGREDIENT.name())) {
                    Ingredient ingredient = ingredientDAO.getById(id);
                    list.put(ingredient, quantity);
                } else {
                    Food food = foodDAO.getById(id);
                    list.put(food, quantity);
                }
            }
        }
        return list;
    }


    void insert(int food_parent_id, int food_child_id, Double quantity) {
        db.execSQL(QUERY_INSERT, new String[]{
                String.valueOf(food_parent_id), String.valueOf(food_child_id), String.valueOf(quantity)
        });
    }


    private Map<Edible, Double> adjustQuantities(Map<? extends Edible, Double> list, Double oldTotalQuantity, Double newTotalQuantity) {
        Map<Edible, Double> newList = new HashMap<Edible, Double>();
        for (Edible e : list.keySet()) {
            Double oldQuantity = list.get(e);
            Double newQuantity = (newTotalQuantity * oldQuantity) / oldTotalQuantity;
            newList.put(e, newQuantity);
            if (e instanceof Ingredient) {
                ((Ingredient) e).ajustToQuantity(newQuantity);
            }
        }
        return newList;
    }


}
