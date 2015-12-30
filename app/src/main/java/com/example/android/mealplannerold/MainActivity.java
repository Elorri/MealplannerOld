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
import com.example.android.mealplannerold.model.FoodType;
import com.example.android.mealplannerold.model.Ingredient;
import com.example.android.mealplannerold.model.Recipe;
import com.example.android.mealplannerold.model.db.FoodDAO;
import com.example.android.mealplannerold.model.db.FoodQuantityDAO;
import com.example.android.mealplannerold.model.db.IngredientDAO;
import com.example.android.mealplannerold.model.db.RecipeDAO;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {


    private TextView label = null;

    //TODO virer ces lignes de code
    public static final Integer R_RAW_INGREDIENTS = R.raw.ingredients;
    public static final Integer R_RAW_RECIPES = R.raw.recipes;
    public static final Integer R_RAW_FOODS = R.raw.foods;
    public static final Integer R_RAW_FOOD_QUANTITY = R.raw.food_quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        try {
            FoodDAO foodDAO = new FoodDAO(this);
            FoodQuantityDAO foodQuantityDAO = new FoodQuantityDAO(this);
            RecipeDAO recipeDAO = new RecipeDAO(this);
            IngredientDAO ingredientDAO = new IngredientDAO(this);

            //addDaysManually(foodDAO, foodQuantityDAO, recipeDAO, ingredientDAO);

            String message = "";

            Double quantity = 1000.0; //quantité nourriture mangée par jour
            for (Edible day : foodDAO.getAllDays()) {
                day.fetchAll(quantity, foodDAO, foodQuantityDAO, recipeDAO, ingredientDAO);
                message = day.toStringFull(quantity, message);
            }
            Tools.echo("\n message is : " + message);
            label = new TextView(this);
            label.setText(message);
            setContentView(label);
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }

    private void addDaysManually(FoodDAO foodDAO, FoodQuantityDAO foodQuantityDAO, RecipeDAO recipeDAO, IngredientDAO ingredientDAO) {
        Ingredient i1 = new Ingredient("Apple juice, frozen concentrate, unsweetened, undiluted, without added ascorbic acid", "no tags", 100, 166, 41, 0.51, 0.37);
        Ingredient i2 = new Ingredient("Apples, raw, with skin", "no tags", 100, 52, 13.81, 0.26, 0.17);
        Ingredient i3 = new Ingredient("Applesauce, canned, unsweetened, without added ascorbic acid (includes USDA commodity)", "no tags", 100, 42, 11.27, 0.17, 0.1);
        Ingredient i4 = new Ingredient("Apricots, dried, sulfured, uncooked", "no tags", 100, 241, 62.64, 3.39, 0.51);
        Ingredient i5 = new Ingredient("Bananas, raw", "no tags", 100, 89, 22.84, 1.09, 0.33);
        Ingredient i6 = new Ingredient("Basil, fresh", "no tags", 100, 23, 2.65, 3.15, 0.64);
        Ingredient i7 = new Ingredient("Beans, snap, green, raw", "no tags", 100, 31, 6.97, 1.83, 0.22);
        Ingredient i8 = new Ingredient("Carrots, raw", "no tags", 100, 41, 9.58, 0.93, 0.24);
        Ingredient i9 = new Ingredient("Celery, raw", "no tags", 100, 16, 2.97, 0.69, 0.17);
        Ingredient i10 = new Ingredient("Cereals ready-to-eat, HEALTH VALLEY, FIBER 7 Flakes", "no tags", 100, 353, 78.15, 14.44, 1.41);
        Ingredient i11 = new Ingredient("Cereals ready-to-eat, rice, puffed, fortified", "no tags", 100, 402, 89.8, 6.3, 0.5);
        Ingredient i12 = new Ingredient("Chives, raw", "no tags", 100, 30, 4.35, 3.27, 0.73);
        Ingredient i13 = new Ingredient("Coriander (cilantro) leaves, raw", "no tags", 100, 23, 3.67, 2.13, 0.52);
        Ingredient i14 = new Ingredient("Egg, white, raw, fresh", "no tags", 100, 52, 0.73, 10.9, 0.17);
        Ingredient i15 = new Ingredient("Figs, dried, uncooked", "no tags", 100, 249, 63.87, 3.3, 0.93);
        Ingredient i16 = new Ingredient("Fish, haddock, smoked", "no tags", 100, 116, 0, 25.23, 0.96);
        Ingredient i17 = new Ingredient("Garlic, raw", "no tags", 100, 149, 33.06, 6.36, 0.5);
        Ingredient i18 = new Ingredient("Ginger root, raw", "no tags", 100, 80, 17.77, 1.82, 0.75);
        Ingredient i19 = new Ingredient("Honey", "no tags", 100, 304, 82.4, 0.3, 0);
        Ingredient i20 = new Ingredient("Lemon juice, raw", "no tags", 100, 22, 6.9, 0.35, 0.24);
        Ingredient i21 = new Ingredient("Lemon peel, raw", "no tags", 100, 47, 16, 1.5, 0.3);
        Ingredient i22 = new Ingredient("Limes, raw", "no tags", 100, 30, 10.54, 0.7, 0.2);
        Ingredient i23 = new Ingredient("Milk substitutes, fluid, with lauric acid oil", "no tags", 100, 61, 6.16, 1.75, 3.41);
        Ingredient i24 = new Ingredient("Millet, puffed", "no tags", 100, 354, 80, 13, 3.4);
        Ingredient i25 = new Ingredient("Mollusks, scallop, mixed species, raw", "no tags", 100, 69, 3.18, 12.06, 0.49);
        Ingredient i26 = new Ingredient("Mushrooms, white, raw", "no tags", 100, 22, 3.26, 3.09, 0.34);
        Ingredient i27 = new Ingredient("Nuts, almonds, dry roasted, without salt added", "no tags", 100, 595, 21.2, 21.06, 52.05);
        Ingredient i28 = new Ingredient("Oil, olive, salad or cooking", "no tags", 100, 884, 0, 0, 100);
        Ingredient i29 = new Ingredient("Onions, raw", "no tags", 100, 40, 9.34, 1.1, 0.1);
        Ingredient i30 = new Ingredient("Orange peel, raw", "no tags", 100, 97, 25, 1.5, 0.2);
        Ingredient i31 = new Ingredient("Parsley, fresh", "no tags", 100, 36, 6.33, 2.97, 0.79);
        Ingredient i32 = new Ingredient("Pears, raw", "no tags", 100, 57, 15.23, 0.36, 0.14);
        Ingredient i33 = new Ingredient("Peas, green, raw", "no tags", 100, 81, 14.45, 5.42, 0.4);
        Ingredient i34 = new Ingredient("Peppers, hot chili, red, raw", "no tags", 100, 40, 8.81, 1.87, 0.44);
        Ingredient i35 = new Ingredient("Peppers, sweet, red, raw", "no tags", 100, 31, 6.03, 0.99, 0.3);
        Ingredient i36 = new Ingredient("Plums, dried (prunes), uncooked", "no tags", 100, 240, 63.88, 2.18, 0.38);
        Ingredient i37 = new Ingredient("Potato flour", "no tags", 100, 357, 83.1, 6.9, 0.34);
        Ingredient i38 = new Ingredient("Quinoa, cooked", "no tags", 100, 120, 21.3, 4.4, 1.92);
        Ingredient i39 = new Ingredient("Raisins, seedless", "no tags", 100, 299, 79.18, 3.07, 0.46);
        Ingredient i40 = new Ingredient("Rice flour, white", "no tags", 100, 366, 80.13, 5.95, 1.42);
        Ingredient i41 = new Ingredient("Rice noodles, cooked", "no tags", 100, 109, 24.9, 0.91, 0.2);
        Ingredient i42 = new Ingredient("Rice, brown, long-grain, cooked", "no tags", 100, 111, 22.96, 2.58, 0.9);
        Ingredient i43 = new Ingredient("SILK Plain soy yogurt", "no tags", 100, 66, 9.69, 2.64, 1.76);
        Ingredient i44 = new Ingredient("SWANSON BROTH, Vegetable Broth", "no tags", 100, 5, 1.28, 0, 0);
        Ingredient i45 = new Ingredient("Seeds, flaxseed", "no tags", 100, 534, 28.88, 18.29, 42.16);
        Ingredient i46 = new Ingredient("Seeds, pumpkin and squash seed kernels, dried", "no tags", 100, 559, 10.71, 30.23, 49.05);
        Ingredient i47 = new Ingredient("Seeds, pumpkin and squash seeds, whole, roasted, with salt added", "no tags", 100, 446, 53.75, 18.55, 19.4);
        Ingredient i48 = new Ingredient("Seeds, sesame seeds, whole, dried", "no tags", 100, 573, 23.45, 17.73, 49.67);
        Ingredient i49 = new Ingredient("Seeds, sunflower seed kernels, toasted, with salt added", "no tags", 100, 619, 20.59, 17.21, 56.8);
        Ingredient i50 = new Ingredient("Spearmint, fresh", "no tags", 100, 44, 8.41, 3.29, 0.73);
        Ingredient i51 = new Ingredient("Spices, nutmeg, ground", "no tags", 100, 525, 49.29, 5.84, 36.31);
        Ingredient i52 = new Ingredient("Spices, paprika", "no tags", 100, 282, 53.99, 14.14, 12.89);
        Ingredient i53 = new Ingredient("Spices, pepper, black", "no tags", 100, 251, 63.95, 10.39, 3.26);
        Ingredient i54 = new Ingredient("Spinach, raw", "no tags", 100, 23, 3.63, 2.86, 0.39);
        Ingredient i55 = new Ingredient("Syrups, maple", "no tags", 100, 260, 67.04, 0.04, 0.06);
        Ingredient i56 = new Ingredient("Tomatoes, red, ripe, raw, year round average", "no tags", 100, 18, 3.89, 0.88, 0.2);
        Ingredient i57 = new Ingredient("Vanilla extract", "no tags", 100, 288, 12.65, 0.06, 0.06);


        foodDAO.add(i1, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i2, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i3, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i4, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i5, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i6, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i7, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i8, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i9, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i10, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i11, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i12, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i13, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i14, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i15, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i16, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i17, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i18, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i19, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i20, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i21, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i22, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i23, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i24, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i25, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i26, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i27, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i28, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i29, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i30, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i31, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i32, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i33, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i34, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i35, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i36, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i37, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i38, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i39, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i40, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i41, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i42, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i43, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i44, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i45, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i46, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i47, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i48, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i49, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i50, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i51, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i52, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i53, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i54, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i55, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i56, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(i57, foodQuantityDAO, recipeDAO, ingredientDAO);


        Recipe r1 = new Recipe("Mixed Cereal Muesli with Grated Apples", "basic recipe", "cf ONC");
        Recipe r2 = new Recipe("Smoked haddock kedgeree with grilled tomatoes", "basic recipe", "ONC");
        Recipe r3 = new Recipe("Quinoa Porridge with Bananas", "basic recipe", "cf ONC");
        Recipe r4 = new Recipe("Millet or rice flake porridge", "basic recipe", "cf ONC");
        Recipe r5 = new Recipe("Dried fruit compôte with orange", "basic recipe", "ONC");
        Recipe r6 = new Recipe("Fruit crumble", "basic recipe", "ONC");
        Recipe r7 = new Recipe("Lime and apple drink", "basic recipe", "ONC");
        Recipe r8 = new Recipe("Ginger and apple blender drink", "basic recipe", "ONC");
        Recipe r9 = new Recipe("Grilled scallops with green sauce julienne vegetables and rice noodles", "basic recipe", "ONC");
        Recipe r10 = new Recipe("Mushroom stroganoff with rice and quinoa pilaf", "basic recipe", "ONC");


        Map<Edible, Double> r1Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r2Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r3Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r4Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r5Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r6Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r7Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r8Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r9Ings = new HashMap<Edible, Double>();
        Map<Edible, Double> r10Ings = new HashMap<Edible, Double>();


        r1Ings.put(i10, 212.0);
        r1Ings.put(i2, 212.0);
        r1Ings.put(i32, 212.0);
        r1Ings.put(i39, 21.0);
        r1Ings.put(i45, 10.0);
        r1Ings.put(i47, 10.0);
        r1Ings.put(i48, 10.0);
        r1Ings.put(i49, 10.0);
        r2Ings.put(i14, 41.0);
        r2Ings.put(i16, 205.0);
        r2Ings.put(i17, 6.0);
        r2Ings.put(i31, 27.0);
        r2Ings.put(i42, 137.0);
        r2Ings.put(i52, 6.0);
        r2Ings.put(i56, 274.0);
        r3Ings.put(i23, 59.0);
        r3Ings.put(i38, 222.0);
        r3Ings.put(i43, 118.0);
        r3Ings.put(i5, 296.0);
        r3Ings.put(i51, 2.0);
        r4Ings.put(i11, 49.0);
        r4Ings.put(i19, 28.0);
        r4Ings.put(i23, 565.0);
        r4Ings.put(i24, 49.0);
        r4Ings.put(i45, 1.0);
        r4Ings.put(i46, 1.0);
        r4Ings.put(i48, 1.0);
        r4Ings.put(i49, 1.0);
        r4Ings.put(i57, 1.0);
        r5Ings.put(i15, 225.0);
        r5Ings.put(i30, 22.0);
        r5Ings.put(i36, 225.0);
        r5Ings.put(i4, 225.0);
        r6Ings.put(i2, 396.0);
        r6Ings.put(i20, 19.0);
        r6Ings.put(i21, 19.0);
        r6Ings.put(i24, 98.0);
        r6Ings.put(i27, 49.0);
        r6Ings.put(i39, 19.0);
        r6Ings.put(i40, 49.0);
        r6Ings.put(i55, 49.0);
        r7Ings.put(i18, 11.0);
        r7Ings.put(i2, 118.0);
        r7Ings.put(i22, 83.0);
        r7Ings.put(i50, 11.0);
        r8Ings.put(i18, 16.0);
        r8Ings.put(i2, 162.0);
        r8Ings.put(i20, 32.0);
        r8Ings.put(i3, 489.0);
        r9Ings.put(i12, 27.0);
        r9Ings.put(i13, 23.0);
        r9Ings.put(i18, 4.0);
        r9Ings.put(i25, 93.0);
        r9Ings.put(i28, 9.0);
        r9Ings.put(i29, 18.0);
        r9Ings.put(i35, 93.0);
        r9Ings.put(i37, 18.0);
        r9Ings.put(i41, 116.0);
        r9Ings.put(i43, 9.0);
        r9Ings.put(i54, 69.0);
        r9Ings.put(i6, 13.0);
        r9Ings.put(i7, 93.0);
        r9Ings.put(i8, 93.0);
        r9Ings.put(i9, 18.0);
        r7Ings.put(i1, 476.0);
        r10Ings.put(i13, 21.0);
        r10Ings.put(i17, 4.0);
        r10Ings.put(i26, 152.0);
        r10Ings.put(i28, 14.0);
        r10Ings.put(i29, 71.0);
        r10Ings.put(i33, 21.0);
        r10Ings.put(i34, 2.0);
        r10Ings.put(i37, 7.0);
        r10Ings.put(i38, 35.0);
        r10Ings.put(i42, 49.0);
        r10Ings.put(i43, 35.0);
        r10Ings.put(i44, 213.0);
        r10Ings.put(i53, 1.0);
        r10Ings.put(i56, 71.0);


        r1.setChildrenFood(r1Ings);
        r2.setChildrenFood(r2Ings);
        r3.setChildrenFood(r3Ings);
        r4.setChildrenFood(r4Ings);
        r5.setChildrenFood(r5Ings);
        r6.setChildrenFood(r6Ings);
        r7.setChildrenFood(r7Ings);
        r8.setChildrenFood(r8Ings);
        r9.setChildrenFood(r9Ings);
        r10.setChildrenFood(r10Ings);


        foodDAO.add(r1, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r2, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r3, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r4, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r5, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r6, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r7, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r8, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r9, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(r10, foodQuantityDAO, recipeDAO, ingredientDAO);

        Food d1 = new Food("Fruit crumble", "no tags", FoodType.DISH.name());
        Food d2 = new Food("Smoked haddock kedgeree with grilled tomatoes", "no tags", FoodType.DISH.name());
        Food d3 = new Food("Lime and apple drink", "no tags", FoodType.DISH.name());
        Food d4 = new Food("Mushroom stroganoff with rice and quinoa pilaf", "no tags", FoodType.DISH.name());
        Food d5 = new Food("Grilled scallops with green sauce julienne vegetables and rice noodles", "no tags", FoodType.DISH.name());
        Food d6 = new Food("Dried fruit compôte with orange", "no tags", FoodType.DISH.name());
        Food d7 = new Food("Quinoa Porridge with Bananas", "no tags", FoodType.DISH.name());
        Food d8 = new Food("Millet or rice flake porridge", "no tags", FoodType.DISH.name());
        Food d9 = new Food("Mixed Cereal Muesli with Grated Apples", "no tags", FoodType.DISH.name());
        Food d10 = new Food("Ginger and apple blender drink", "no tags", FoodType.DISH.name());


        Map<Edible, Double> d1Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d2Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d3Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d4Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d5Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d6Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d7Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d8Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d9Recipes = new HashMap<Edible, Double>();
        Map<Edible, Double> d10Recipes = new HashMap<Edible, Double>();

        d1Recipes.put(r6, 700.0);
        d2Recipes.put(r2, 700.0);
        d3Recipes.put(r7, 700.0);
        d4Recipes.put(r10, 700.0);
        d5Recipes.put(r9, 700.0);
        d6Recipes.put(r5, 700.0);
        d7Recipes.put(r3, 700.0);
        d8Recipes.put(r4, 700.0);
        d9Recipes.put(r1, 700.0);
        d10Recipes.put(r8, 700.0);


        d1.setChildrenFood(d1Recipes);
        d2.setChildrenFood(d2Recipes);
        d3.setChildrenFood(d3Recipes);
        d4.setChildrenFood(d4Recipes);
        d5.setChildrenFood(d5Recipes);
        d6.setChildrenFood(d6Recipes);
        d7.setChildrenFood(d7Recipes);
        d8.setChildrenFood(d8Recipes);
        d9.setChildrenFood(d9Recipes);
        d10.setChildrenFood(d10Recipes);

        foodDAO.add(d1, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d2, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d3, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d4, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d5, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d6, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d7, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d8, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d9, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(d10, foodQuantityDAO, recipeDAO, ingredientDAO);


        Food m1 = new Food("Meal 1", "no tags", FoodType.MEAL.name());
        Food m2 = new Food("Meal 2", "no tags", FoodType.MEAL.name());
        Food m3 = new Food("Meal 3", "no tags", FoodType.MEAL.name());
        Food m4 = new Food("Meal 4", "no tags", FoodType.MEAL.name());
        Food m5 = new Food("Meal 5", "no tags", FoodType.MEAL.name());
        Food m6 = new Food("Meal 6", "no tags", FoodType.MEAL.name());


        Map<Edible, Double> m1Dishes = new HashMap<Edible, Double>();
        Map<Edible, Double> m2Dishes = new HashMap<Edible, Double>();
        Map<Edible, Double> m3Dishes = new HashMap<Edible, Double>();
        Map<Edible, Double> m4Dishes = new HashMap<Edible, Double>();
        Map<Edible, Double> m5Dishes = new HashMap<Edible, Double>();
        Map<Edible, Double> m6Dishes = new HashMap<Edible, Double>();

        m1Dishes.put(d10, 350.0);
        m1Dishes.put(d8, 350.0);
        m5Dishes.put(d10, 350.0);
        m5Dishes.put(d9, 350.0);
        m3Dishes.put(d3, 350.0);
        m3Dishes.put(d7, 350.0);
        m2Dishes.put(d6, 233.0);
        m2Dishes.put(d4, 233.0);
        m6Dishes.put(d6, 233.0);
        m6Dishes.put(d5, 233.0);
        m4Dishes.put(d1, 233.0);
        m4Dishes.put(d2, 233.0);

        m1.setChildrenFood(m1Dishes);
        m2.setChildrenFood(m2Dishes);
        m3.setChildrenFood(m3Dishes);
        m4.setChildrenFood(m4Dishes);
        m5.setChildrenFood(m5Dishes);
        m6.setChildrenFood(m6Dishes);

        foodDAO.add(m1, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(m2, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(m3, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(m4, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(m5, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(m6, foodQuantityDAO, recipeDAO, ingredientDAO);

        Food day1 = new Food("Day 1", "no tags", FoodType.DAY.name());
        Food day2 = new Food("Day 2", "no tags", FoodType.DAY.name());
        Food day3 = new Food("Day 3", "no tags", FoodType.DAY.name());

        Map<Edible, Double> day1Meals = new HashMap<Edible, Double>();
        Map<Edible, Double> day2Meals = new HashMap<Edible, Double>();
        Map<Edible, Double> day3Meals = new HashMap<Edible, Double>();

        day1Meals.put(m1, 700.0);
        day3Meals.put(m3, 700.0);
        day2Meals.put(m5, 700.0);
        day1Meals.put(m6, 466.0);
        day3Meals.put(m4, 466.0);
        day2Meals.put(m2, 466.0);

        day1.setChildrenFood(day1Meals);
        day2.setChildrenFood(day2Meals);
        day3.setChildrenFood(day3Meals);

        foodDAO.add(day1, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(day2, foodQuantityDAO, recipeDAO, ingredientDAO);
        foodDAO.add(day3, foodQuantityDAO, recipeDAO, ingredientDAO);
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
