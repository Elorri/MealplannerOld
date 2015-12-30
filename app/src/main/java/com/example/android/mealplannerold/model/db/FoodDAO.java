package com.example.android.mealplannerold.model.db;

import android.content.Context;
import android.database.Cursor;

import com.example.android.mealplannerold.model.Edible;
import com.example.android.mealplannerold.model.Food;
import com.example.android.mealplannerold.model.FoodType;
import com.example.android.mealplannerold.model.Ingredient;
import com.example.android.mealplannerold.model.Recipe;
import com.example.android.mealplannerold.model.exception.NoFoodChildrenException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.sql.SQLException;

/**
 * Created by Elorri on 30/12/2015.
 */
public class FoodDAO extends DAO {

    public FoodDAO(Context context){
        super(context);
    }

    private static String TABLE = "FOOD";

    public static String QUERY_CREATION =
            "CREATE TABLE " + TABLE + "(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT(200), " +
                    "TAGS TEXT(400), TYPE TEXT(10))";

    private static String QUERY_INSERT = "INSERT INTO " + TABLE + " (NAME, TAGS, TYPE) " + "VALUES (?, ?, ?)";
    public static String QUERY_INSERT_WITH_ID =
            "INSERT INTO " + TABLE + " (ID, NAME, TAGS, TYPE) " + "VALUES (?, ?, ?, ?)";

    private static String QUERY_UPDATE = "UPDATE " + TABLE + " SET NAME=?, TAGS=? , TYPE=?" + "WHERE ID=?";

    private static String QUERY_DELETE = "DELETE FROM " + TABLE + " d WHERE d.ID = ?";

    private static String QUERY_SEARCH_BY_ID = "SELECT * FROM " + TABLE + " t WHERE t.ID = ?";
    private static String QUERY_SEARCH_BY_TYPE = "SELECT * FROM " + TABLE + " t WHERE t.TYPE = ?";
    private static String QUERY_SEARCH_ID_BY_NAME_N_TYPE = "SELECT ID FROM " + TABLE + " t WHERE t.NAME = ? AND TYPE=?";

    private static String QUERY_SEARCH_ALL = "SELECT * FROM " + TABLE;





    public List<Food> getAllDays() {
        return getAll(FoodType.DAY.name());
    }

    private  List<Food> getAll(String type) {
        List<Food> list = new ArrayList<Food>();
        try {
            Cursor results = db.rawQuery(QUERY_SEARCH_BY_TYPE, new String[]{type});
            list = extract(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public  Food getById(int id) {
        List<Food> list = new ArrayList<Food>();
        try {
            Cursor results = db.rawQuery(QUERY_SEARCH_BY_ID, new String[]{String.valueOf(id)});
            list = extract(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }

    private static List<Food> extract(Cursor results) throws SQLException {
        List<Food> list = new ArrayList<Food>();
        while (results.moveToNext()) {
            Integer id = results.getInt(0);
            String name = results.getString(1);
            String tags = results.getString(2);
            String type = results.getString(3);
            Food food = new Food(id, name, tags, type);
            list.add(food);
        }
        return list;
    }


    /**
     * This method add the food parent and its children to the tables FOOD and FOOD_QUANTITY except if the food can't have childrens (case of Food Ingredient).
     *
     * @param food
     */
    public void add(Food food) {
         /* try {
          insert(food);
            int foodId = getIdByName(food.getName(), food.getType());
            food.setId(foodId);

            if (food instanceof Ingredient) {
                Ingredient i = (Ingredient) food;
                IngredientDAO.insert(i);
            } else {
                if (food instanceof Recipe) {
                    Recipe r = (Recipe) food;
                    RecipeDAO.insert(r);
                }
                addChildrens(food);

            }
        } catch (NoFoodChildrenException e) {
            e.printStackTrace();
        }*/

    }


    private  int getIdByName(String name, String type) {
        Cursor results = db.rawQuery(QUERY_SEARCH_ID_BY_NAME_N_TYPE, new String[]{name, type});
        results.moveToNext();
        return results.getInt(0);

    }


    private static <F extends Edible> void addChildrens(Food food) throws NoFoodChildrenException {
        Map<Edible, Integer> childrenList = food.getChildrenFood();
        Map<Recipe, Integer> recipes = food.getRecipes();
        Map<Ingredient, Integer> ingredients = food.getIngredients();
        if ((food.getType().equals(FoodType.DISH.name())) && (recipes == null))
            throw new NoFoodChildrenException();
        if ((food.getType().equals(FoodType.RECIPE.name())) && (ingredients == null))
            throw new NoFoodChildrenException();
        if (!(food.getType().equals(FoodType.DISH.name())) && !(food.getType().equals(FoodType.RECIPE.name())) &&
                (childrenList == null))
            throw new NoFoodChildrenException();


  /*      if (food.getType().equals(FoodType.DISH.name())) {
            for (Recipe recipe : (Set<Recipe>) recipes.keySet()) {
                int quantity = recipes.get(recipe);
                FoodQuantityDAO.insert(food.getId(), recipe.getId(), quantity);
            }
        }
        if (food.getType().equals(FoodType.RECIPE.name())) {
            for (Ingredient ingredient : (Set<Ingredient>) ingredients.keySet()) {
                int quantity = ingredients.get(ingredient);
                FoodQuantityDAO.insert(food.getId(), ingredient.getId(), quantity);
            }
        }
        if (!(food.getType().equals(FoodType.DISH.name())) && !(food.getType().equals(FoodType.RECIPE.name()))) {
            for (Edible childrenFood : (Set<Edible>) childrenList.keySet()) {
                int quantity = childrenList.get(childrenFood);
                FoodQuantityDAO.insert(food.getId(), childrenFood.getId(), quantity);
            }
        }*/
    }

    void insert(Food food) {
        db.execSQL(QUERY_INSERT, new String[]{food.getName(), food.getTags(), food.getType()});
    }

}