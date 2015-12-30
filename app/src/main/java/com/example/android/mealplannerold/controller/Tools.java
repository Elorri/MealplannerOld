package com.example.android.mealplannerold.controller;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Elorri on 30/12/2015.
 */
public class Tools {
    private static Tools instance=null;
    //private MainActivity mainActivity;
    private Context context;

/*    public Tools(MainActivity ma)    {
       this.mainActivity=ma;
    }*/

    public Tools(Context context)    {
        this.context=context;
    }

    /**
     * Load a text file .cvs, .txt, ... and return a collection of its lines.
     * @param resourceId located in the directory res and automatically referenced with an int
     * @return a collection where each item is a String representing one line of a the file.
     */
    public ArrayList<String> loadTextFile(int resourceId) {
        // The InputStream opens the resourceId and sends it to the buffer
        ArrayList<String> listOfLines=new ArrayList<String>();
        //InputStream is = mainActivity.getResources().openRawResource(resourceId);
        InputStream is = context.getResources().openRawResource(resourceId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;

        try {
            // While the BufferedReader line is not null
            while ((line = br.readLine()) != null) {
                Log.d("MealPlanner", line);
                listOfLines.add(line);
            }

            // Close the InputStream and BufferedReader
            is.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfLines;
    }

    public static void echo(String s) {
        int length = s.length();

        for(int i=0; i<length; i+=3000)
        {
            if(i+3000<length)
                Log.e("MealPlanner", s.substring(i, i + 3000));
            else
                Log.e("MealPlanner", s.substring(i, length));
        }
    }


 /*   public static Tools instance()  {
        if(instance == null) {
            instance = new Tools();
        }
        return instance;
    }*/
}