package com.example.android.mealplannerold.model.db;

import android.content.Context;
import android.database.Cursor;

import com.example.android.mealplannerold.model.Edible;
import com.example.android.mealplannerold.model.Food;
import com.example.android.mealplannerold.model.Ingredient;
import com.example.android.mealplannerold.model.Recipe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elorri on 30/12/2015.
 */
public class FoodQuantityDAO extends DAO {
    private FoodDAO foodDAO;
    private RecipeDAO recipeDAO;
    private IngredientDAO ingredientDAO;

    //TODO enlever RecipeDAO et IngredientDAO du constructeur.
    public FoodQuantityDAO(Context context, FoodDAO foodDAO, RecipeDAO recipeDAO, IngredientDAO ingredientDAO){
        super(context);
        this.foodDAO = foodDAO;
        this.recipeDAO=recipeDAO;
        this.ingredientDAO=ingredientDAO;
    }

    private static String TABLE = "FOOD_QUANTITY";

    public static String QUERY_CREATION =
            "CREATE TABLE " + TABLE + "(" + "ID_FOOD_PARENT INTEGER, " + "ID_FOOD_CHILDREN INTEGER, " +
                    "QUANTITY INTEGER, " + "PRIMARY KEY(ID_FOOD_PARENT, ID_FOOD_CHILDREN) " +
                    "FOREIGN KEY (ID_FOOD_PARENT) REFERENCES FOOD(ID)  " + "FOREIGN KEY (ID_FOOD_CHILDREN) REFERENCES FOOD(ID)  " +
                    "CHECK (QUANTITY>0))";


    public  static String QUERY_INSERT =
            "INSERT INTO " + TABLE + " (ID_FOOD_PARENT, ID_FOOD_CHILDREN, QUANTITY) " + "VALUES (?, ?, ?)";

    private static String QUERY_UPDATE =
            "UPDATE " + TABLE + " SET QUANTITY=?" + "WHERE ID_FOOD_PARENT=? AND ID_FOOD_CHILDREN=?";

    private static String QUERY_DELETE = "DELETE FROM " + TABLE + " WHERE ID_FOOD_PARENT=?";

    private static String QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT =
            "SELECT ID_FOOD_CHILDREN, QUANTITY FROM " + TABLE + " t WHERE t.ID_FOOD_PARENT = ?";

    private static String QUERY_SEARCH_ALL = "SELECT * FROM " + TABLE;





    //TODO add fetchMeals(day, quantity) et les autres fetch avec la quantité ajustée
    public  void fetchMeals(Edible day) {
        fetchChildrenFood(day);
    }

    public  void fetchDishes(Edible meal) {
        fetchChildrenFood(meal);
    }

    public  void fetchRecipes(Edible dish) {
        dish.setRecipes(getRecipes(dish));
    }

    public  void fetchIngredients(Edible recipe) {
        recipe.setIngredients(getIngredients(recipe));
    }

    public  void fetchChildrenFood(Edible parentFood) {
        parentFood.setChildrenFood(getChildrenFood(parentFood));
    }

/*
    //TODO implementer correctement cette methode
    public static void fetchMeals(Edible day, int quantity) {
        fetchChildrenFood(day, quantity);
    }

    //TODO implementer correctement cette methode
    public static void fetchDishes(Edible meal, int quantity) {
        fetchChildrenFood(meal, quantity);
    }

    //TODO implementer correctement cette methode
    public static void fetchRecipes(Edible dish, int quantity) {
        dish.setRecipes(getRecipes(dish, quantity));
    }
    //TODO implementer correctement cette methode
    public static void fetchIngredients(Edible recipe, int quantity) {
        recipe.setIngredients(getIngredients(recipe, quantity));
    }
    //TODO implementer correctement cette methode
    public static void fetchChildrenFood(Edible parentFood, int quantity) {
        parentFood.setChildrenFood(getChildrenFood(parentFood, quantity));
    }
*/

    private Map<Edible, Integer> getChildrenFood(Edible parentFood) {
        Map<Edible, Integer> list = new HashMap<Edible, Integer>();
        Cursor results = db.rawQuery(QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT, new String[]{
                String.valueOf(parentFood.getId())});
        list = extractFood(results);
        return list;
    }

    private  Map<Recipe, Integer> getRecipes(Edible dish) {
        Map<Recipe, Integer> list = new HashMap<Recipe, Integer>();
        Cursor results = db.rawQuery(QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT, new String[]{
                String.valueOf(dish.getId())});
        list = extractRecipes(results);
        return list;
    }

    private  Map<Ingredient, Integer> getIngredients(Edible parentFood) {
        Map<Ingredient, Integer> list = new HashMap<Ingredient, Integer>();
        Cursor results = db.rawQuery(QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT, new String[] {
                String.valueOf(parentFood.getId()) });
        list = extractIngredients(results);
        return list;
    }
/*
    private  Map<Edible, Integer> getChildrenFood(Edible parentFood, int quantity) {
        Map<Edible, Integer> list = new HashMap<Edible, Integer>();
        Cursor results = db.rawQuery(QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT, new String[] {
                String.valueOf(parentFood.getId()) });
        list = extractFood(results);
        list=adjustQuantities(list, quantity);
        return list;
    }

    private static Map<Recipe, Integer> getRecipes(Edible dish, int quantity) {
        Map<Recipe, Integer> list = new HashMap<Recipe, Integer>();
        Cursor results = db.rawQuery(QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT, new String[] {
                String.valueOf(dish.getId()) });
        list = extractRecipes(results);
        list=adjustQuantities(list, quantity);
        return list;
    }

    private static Map<Ingredient, Integer> getIngredients(Edible parentFood, int quantity) {
        Map<Ingredient, Integer> list = new HashMap<Ingredient, Integer>();
        Cursor results = db.rawQuery(QUERY_SEARCH_FOOD_CHILDREN_BY_FOOD_PARENT, new String[] {
                String.valueOf(parentFood.getId()) });
        list = extractIngredients(results);
        list=adjustQuantities(list, quantity);
        return list;
    }*/


    private  Map<Edible, Integer> extractFood(Cursor results) {
        Map<Edible, Integer> list = new HashMap<Edible, Integer>();
        while (results.moveToNext()) {
            Integer id = results.getInt(0);
            Integer quantity = results.getInt(1);
            Food food = foodDAO.getById(id);
            list.put(food, quantity);
        }
        return list;
    }

    private  Map<Recipe, Integer> extractRecipes(Cursor results)  {
        Map<Recipe, Integer> list = new HashMap<Recipe, Integer>();
        while (results.moveToNext()) {
            Integer id = results.getInt(0);
            Integer quantity = results.getInt(1);
            Recipe recipe = recipeDAO.getById(id);
            list.put(recipe, quantity);
        }
        return list;
    }

    private  Map<Ingredient, Integer> extractIngredients(Cursor results) {
        Map<Ingredient, Integer> list = new HashMap<Ingredient, Integer>();
        while (results.moveToNext()) {
            Integer id = results.getInt(0);
            Integer quantity = results.getInt(1);
            Ingredient ingredient = ingredientDAO.getById(id);
            list.put(ingredient, quantity);
        }
        return list;
    }





    void insert(int food_parent_id, int food_child_id, int quantity) {
        db.execSQL(QUERY_INSERT, new String[]{
                String.valueOf(food_parent_id), String.valueOf(food_child_id), String.valueOf(quantity)
        });
    }



/*
    private static Map<Edible, Integer> adjustQuantities(Map<Edible, Integer> list, int newTotalQuantity) {
        int oldTotalQuantity=0;
        for(int oldQuantity : list.values()){
            oldTotalQuantity=oldTotalQuantity+oldQuantity;
        }

        Map<Edible, Integer> newList=new HashMap<Edible, Integer>();
        for(Edible e:list.keySet()){
            int oldQuantity=list.get(e);
            int newQuantity=(newTotalQuantity*oldQuantity)/oldTotalQuantity;
            newList.put(e,newQuantity);
        }
        return newList;
    }

    private static Map<Ingredient, Integer> adjustQuantities(Map<Ingredient, Integer> list, int newTotalQuantity) {
        int oldTotalQuantity=0;
        for(int oldQuantity : list.values()){
            oldTotalQuantity=oldTotalQuantity+oldQuantity;
        }

        Map<Ingredient, Integer> newList=new HashMap<Ingredient, Integer>();
        for(Ingredient e:list.keySet()){
            int oldQuantity=list.get(e);
            int newQuantity=(newTotalQuantity*oldQuantity)/oldTotalQuantity;
            newList.put(e,newQuantity);
        }
        return newList;
    }

    private static Map<Recipe, Integer> adjustQuantities(Map<Recipe, Integer> list, int newTotalQuantity) {
        int oldTotalQuantity=0;
        for(int oldQuantity : list.values()){
            oldTotalQuantity=oldTotalQuantity+oldQuantity;
        }

        Map<Recipe, Integer> newList=new HashMap<Recipe, Integer>();
        for(Recipe e:list.keySet()){
            int oldQuantity=list.get(e);
            int newQuantity=(newTotalQuantity*oldQuantity)/oldTotalQuantity;
            newList.put(e,newQuantity);
        }
        return newList;
    }
*/
}
