package com.example.android.mealplannerold.model;

import java.util.Date;

/**
 * Created by Elorri-user on 15/06/2015.
 */
public class CalendarDay {
    private Integer id;
    private Date date;
    private Integer idDay;


    public CalendarDay(Integer id, Date date, Integer idDay) {
        this.id=id;
        this.date=date;
        this.idDay=idDay;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Integer getIdDay() {
        return idDay;
    }
}
