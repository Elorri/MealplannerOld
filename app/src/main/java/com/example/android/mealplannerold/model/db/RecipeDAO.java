package com.example.android.mealplannerold.model.db;

import android.database.Cursor;

import com.example.android.mealplannerold.controller.activities.MainActivity;
import com.example.android.mealplannerold.model.Recipe;
import com.example.android.mealplannerold.model.db.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO extends DAO {
    public RecipeDAO(MainActivity context){
        super(context);
    }




    private static String TABLE = "RECIPES";


    public static String QUERY_CREATION =
        "CREATE TABLE " + TABLE + "(" + "ID INTEGER PRIMARY KEY, " + "NAME TEXT(200), " + "TAGS TEXT(400), " +
        "TYPE TEXT(10), " + "DIRECTIONS TEXT(4000)" + ")";


    public  static String QUERY_INSERT =
        "INSERT INTO " + TABLE + " (ID, NAME, TAGS, TYPE, DIRECTIONS) " + "VALUES (?, ?, ?, ?, ?)";


    private static String QUERY_UPDATE = "UPDATE " + TABLE + " SET NAME=?, DIRECTIONS=?, TAGS=? " + "WHERE ID=?";


    private static String QUERY_DELETE = "DELETE FROM " + TABLE + " r WHERE r.ID = ?";


    private static String QUERY_SEARCH_BY_ID = "SELECT * FROM " + TABLE + " r WHERE r.ID = ?";

    private static String QUERY_SEARCH_ALL = "SELECT * FROM " + TABLE;




    public  List<Recipe> getAll() throws SQLException {
        Cursor results = db.rawQuery(QUERY_SEARCH_ALL, new String[] { });
        List<Recipe> list = extract_recipes(results);
        return list;
    }

    public  Recipe getById(int id){
        Cursor results = db.rawQuery(QUERY_SEARCH_BY_ID, new String[] { String.valueOf(id) });
        List<Recipe> list = extract_recipes(results);
        return list.get(0);

    }

    private List<Recipe> extract_recipes(Cursor results)  {
        List<Recipe> list = new ArrayList<Recipe>();
        while (results.moveToNext()) {
            Integer id = results.getInt(0);
            String name = results.getString(1);
            String tags = results.getString(2);
            String type = results.getString(3);
            String directions = results.getString(4);


            Recipe recipe = new Recipe(id, name, tags, type, directions);
            list.add(recipe);
        }
        // db.close();
        return list;
    }






    void insert(Recipe r) {
        db.execSQL(QUERY_INSERT, new String[] {
                                 String.valueOf(r.getId()), r.getName(), r.getTags(), r.getType(), r.getDirections()
        });
    }

}
