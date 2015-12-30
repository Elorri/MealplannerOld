package com.example.android.mealplannerold;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.mealplannerold.controller.Tools;
import com.example.android.mealplannerold.model.Edible;
import com.example.android.mealplannerold.model.Food;
import com.example.android.mealplannerold.model.Ingredient;
import com.example.android.mealplannerold.model.Recipe;
import com.example.android.mealplannerold.model.db.FoodDAO;
import com.example.android.mealplannerold.model.db.FoodQuantityDAO;
import com.example.android.mealplannerold.model.db.IngredientDAO;
import com.example.android.mealplannerold.model.db.RecipeDAO;

import java.util.Set;

public class MainActivity extends Activity {



    private TextView label = null;

    //TODO virer ces lignes de code
    public static final int R_RAW_INGREDIENTS = R.raw.ingredients;
    public static final int R_RAW_RECIPES = R.raw.recipes;
    public static final int R_RAW_FOODS = R.raw.foods;
    public static final int R_RAW_FOOD_QUANTITY = R.raw.food_quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        try {
            String message = "";


            //AccesBase accesBase = new AccesBase(this);
            //TotoDAO dao=new TotoDAO(this);
            FoodDAO foodDAO = new FoodDAO(this);
            RecipeDAO recipeDAO=new RecipeDAO(this);
            IngredientDAO ingredientDAO=new IngredientDAO(this);
            FoodQuantityDAO foodQuantityDAO = new FoodQuantityDAO(this, foodDAO, recipeDAO, ingredientDAO);


            for (Food day : foodDAO.getAllDays()) {
                message += "\n" + day.toString();
                foodQuantityDAO.fetchMeals(day);
                for (Edible meal : (Set<Edible>) day.getChildrenFood().keySet()) {
                    message += "\n---" + meal.toString();
                    foodQuantityDAO.fetchDishes(meal);
                    for (Edible dish : (Set<Edible>) meal.getChildrenFood().keySet()) {
                        message += "\n------" + dish.toString();
                        foodQuantityDAO.fetchRecipes(dish);
                        for (Recipe recipe : (Set<Recipe>) dish.getRecipes().keySet()) {
                            message += "\n---------" + recipe.toString();
                            foodQuantityDAO.fetchIngredients(recipe);
                            for (Ingredient ingredient : (Set<Ingredient>) recipe.getIngredients().keySet()) {
                                message += "\n------------" + ingredient.toString();
                            }
                        }
                    }
                }
            }
            Tools.echo("\n message is : " + message);
            label = new TextView(this);

            label.setText(message);
            setContentView(label);
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
