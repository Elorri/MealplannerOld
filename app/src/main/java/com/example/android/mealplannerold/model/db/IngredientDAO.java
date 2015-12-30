package com.example.android.mealplannerold.model.db;


import android.database.Cursor;

import com.example.android.mealplannerold.controller.activities.MainActivity;
import com.example.android.mealplannerold.model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO extends DAO {
    public IngredientDAO(MainActivity context){
        super(context);
    }


    private static IngredientDAO instance = null;

    private static String TABLE = "INGREDIENTS";

    public static String QUERY_CREATION =
        "CREATE TABLE " + TABLE + "(" + "ID INTEGER PRIMARY KEY, " + "NAME TEXT(200), " +
        "TAGS TEXT(400), TYPE TEXT(10), QUANTITY INTEGER, " + "ENERGY INTEGER, " + "CARBS REAL, " + "PROTEINS REAL, " +
        "FATS REAL, " + "CHECK (QUANTITY>0))";


    public static String QUERY_INSERT =
        "INSERT INTO " + TABLE + " (ID, NAME, TAGS, TYPE, QUANTITY, ENERGY, CARBS, PROTEINS, FATS) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static String QUERY_UPDATE =
        "UPDATE " + TABLE + " SET QUANTITY=?, NAME=?, ENERGY=?, CARBS=?, PROTEINS=?, FATS=? " + "WHERE ID=?";

    private static String QUERY_DELETE = "DELETE FROM " + TABLE + " u WHERE u.ID = ?";

    private static String QUERY_SEARCH_BY_ID = "SELECT * FROM " + TABLE + " i WHERE i.ID = ?";

    private static String QUERY_SEARCH_ALL = "SELECT * FROM " + TABLE;




    public  List<Ingredient> getAll() throws SQLException {
        Cursor results = db.rawQuery(QUERY_SEARCH_ALL, new String[] { });
        List<Ingredient> list = extract(results);
        return list;
    }

    public  Ingredient getById(int id)  {
        Cursor results = db.rawQuery(QUERY_SEARCH_BY_ID, new String[] { String.valueOf(id) });
        List<Ingredient> list = extract(results);
        return list.get(0);
    }

    private List<Ingredient> extract(Cursor results)  {
        List<Ingredient> list = new ArrayList<Ingredient>();
        while (results.moveToNext()) {
            Integer id = results.getInt(0);
            String name = results.getString(1);
            String tags = results.getString(2);
            String type = results.getString(3);
            double quantity = results.getInt(4);
            double energy = results.getInt(5);
            double carbs = results.getDouble(6);
            double proteins = results.getDouble(7);
            double fats = results.getDouble(8);
            Ingredient ingredient = new Ingredient(id, name, tags, type, quantity, energy, carbs, proteins, fats);
            list.add(ingredient);
        }
        return list;
    }


    public static void clear() {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE + " ;");
    }


     void insert(Ingredient i) {
        db.execSQL(QUERY_INSERT, new String[]{
                String.valueOf(i.getId()), i.getName(), i.getTags(), i.getType(),
                String.valueOf(i.getQuantity()), String.valueOf(i.getEnergy()),
                String.valueOf(i.getCarbs()), String.valueOf(i.getProteins()),
                String.valueOf(i.getFats())
        });
    }
}
