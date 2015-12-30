package com.example.android.mealplannerold.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.mealplannerold.MainActivity;
import com.example.android.mealplannerold.controller.Tools;

/**
 * Created by Elorri on 30/12/2015.
 */
public class AccesBase extends SQLiteOpenHelper {

    private static AccesBase instance = null;
    private Context context;

    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NAME = "mealplanner.db";

/*    private SQLiteDatabase db;
    private FoodDAO foodDAO;
    private FoodQuantityDAO foodQuantityDAO;
    private RecipeDAO recipeDAO;
    private IngredientDAO ingredientDAO;

    public FoodDAO getFoodDAO() {
        return foodDAO;
    }

    public FoodQuantityDAO getFoodQuantityDAO() {
        return foodQuantityDAO;
    }

    public RecipeDAO getRecipeDAO() {
        return recipeDAO;
    }

    public IngredientDAO getIngredientDAO() {
        return ingredientDAO;
    }*/

    public AccesBase(Context context) {
        super(context, NAME, null, VERSION);
        this.context=context;
        Log.e("MealPlanner", "Je suis dans AccesBase constructor ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("MealPlanner", "Je suis dans AccesBase onCreate ");


        db.execSQL(FoodDAO.QUERY_CREATION);
        db.execSQL(FoodQuantityDAO.QUERY_CREATION);
        db.execSQL(IngredientDAO.QUERY_CREATION);
        db.execSQL(RecipeDAO.QUERY_CREATION);
        Log.e("MealPlanner", "All tables created");



        insert(db, FoodDAO.QUERY_INSERT_WITH_ID, MainActivity.R_RAW_FOODS);
        insert(db, FoodQuantityDAO.QUERY_INSERT, MainActivity.R_RAW_FOOD_QUANTITY);
        insert(db, IngredientDAO.QUERY_INSERT, MainActivity.R_RAW_INGREDIENTS);
        insert(db, RecipeDAO.QUERY_INSERT, MainActivity.R_RAW_RECIPES);
        Log.e("MealPlanner", "Inserts done");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("MealPlanner", "Je suis dans AccesBase onUpgrade ");
    }


    public static AccesBase instance(Context context) {
        if (instance == null) instance = new AccesBase(context);
        return instance;
    }

    protected void insert(SQLiteDatabase db, String queryInsert, int ressource_reference) {
        for (String line : (new Tools(context)).loadTextFile(ressource_reference)) {
            db.execSQL(queryInsert, line.split("\\|"));
        }
    }

}
