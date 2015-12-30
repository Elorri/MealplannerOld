package com.example.android.mealplannerold.model.db;


import android.database.Cursor;

import com.example.android.mealplannerold.controller.activities.MainActivity;
import com.example.android.mealplannerold.model.CalendarDay;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarDayDAO extends com.example.android.mealplannerold.model.db.DAO {
    public CalendarDayDAO(MainActivity context){
        super(context);
    }



    private static String TABLE = "CALENDAR_DAYS";

    public static String QUERY_CREATION =
            "CREATE TABLE " + TABLE + "(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "DATE TEXT(19) UNIQUE, " +
                    "ID_DAY INTEGER, FOREIGN KEY (ID_DAY) REFERENCES FOOD(ID))";


    public static String QUERY_INSERT = "INSERT INTO " + TABLE + " (DATE, ID_DAY) VALUES (?, ?)";

    private static String QUERY_UPDATE ="UPDATE " + TABLE + " SET DATE=?, ID_DAY=? WHERE ID=?";

    private static String QUERY_DELETE = "DELETE FROM " + TABLE + " u WHERE u.ID = ?";

    private static String QUERY_SEARCH_BY_ID = "SELECT ID, DATE, ID_DAY FROM " + TABLE + " i WHERE i.ID = ?";

    private static String QUERY_SEARCH_IN_BETWEEN = "SELECT ID, DATE, ID_DAY FROM " + TABLE + " i WHERE strftime('%J',DATE) between strftime('%J',?) AND strftime('%J',?)";

    private static String QUERY_SEARCH_ALL = "SELECT ID, DATE, ID_DAY FROM " + TABLE;




    public List<CalendarDay> getAll() throws SQLException {
        Cursor results = db.rawQuery(QUERY_SEARCH_ALL, new String[] { });
        List<CalendarDay> list = extract(results);
        return list;
    }

    public List<CalendarDay> getInBetween(Date first, Date last) throws SQLException {
        Cursor results = db.rawQuery(QUERY_SEARCH_IN_BETWEEN, new String[] {toString(first), toString(last) });
        List<CalendarDay> list = extract(results);
        return list;
    }

    public  CalendarDay getById(int id)  {
        Cursor results = db.rawQuery(QUERY_SEARCH_BY_ID, new String[] { String.valueOf(id) });
        List<CalendarDay> list = extract(results);
        return list.get(0);
    }

    private List<CalendarDay> extract(Cursor results)  {
        List<CalendarDay> list = new ArrayList<CalendarDay>();
        while (results.moveToNext()) {
            Integer id = results.getInt(0);
            Date date = toDate(results.getString(1));
            Integer idDay= results.getInt(2);
            CalendarDay CalendarDay = new CalendarDay(id, date, idDay);
            list.add(CalendarDay);
        }
        return list;
    }



    void insert(CalendarDay c) {
        db.execSQL(QUERY_INSERT, new String[]{
                String.valueOf(c.getId()), toString(c.getDate()), String.valueOf(c.getIdDay())});
    }

    private Date toDate(String aStringDate){
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            Date aDate =  df.parse(aStringDate);
            return aDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String toString(Date aDate){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            String aStringDate =  df.format(aDate);
            return aStringDate;
    }
}
