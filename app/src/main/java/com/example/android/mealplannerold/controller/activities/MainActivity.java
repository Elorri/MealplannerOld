package com.example.android.mealplannerold.controller.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.mealplannerold.R;
import com.example.android.mealplannerold.controller.adapters.ViewPagerAdapter;
import com.example.android.mealplannerold.controller.fragments.EmptyListFragment;
import com.example.android.mealplannerold.controller.fragments.ListFragment;
import com.example.android.mealplannerold.controller.fragments.pojo.DayList;
import com.example.android.mealplannerold.model.Food;
import com.example.android.mealplannerold.model.db.FoodDAO;
import com.example.android.mealplannerold.model.db.FoodQuantityDAO;
import com.example.android.mealplannerold.model.db.IngredientDAO;
import com.example.android.mealplannerold.model.db.RecipeDAO;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.List;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener{
//implements  FragmentManager.OnBackStackChangedListener


    private FoodDAO foodDAO;
    private FoodQuantityDAO foodQuantityDAO;
    private RecipeDAO recipeDAO;
    private IngredientDAO ingredientDAO;


    private Integer selectedTab;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    public static final String PREF_FILE_NAME = "userpref";

    private ListFragment listFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

            this.foodDAO = new FoodDAO(this);
            this.foodQuantityDAO = new FoodQuantityDAO(this);
            this.recipeDAO = new RecipeDAO(this);
            this.ingredientDAO = new IngredientDAO(this);


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
            viewPager = (ViewPager) findViewById(R.id.viewPager);
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
            tabHost = adapter.addTabs(tabHost, this);
            selectedTab = Integer.valueOf(readFromPreferences(this, "selectedTab", "0"));
            //tabHost.setSelectedNavigationItem(selectedTab);
            viewPager.setAdapter(adapter);
            viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    Log.d("MealPlanner", "in onPageSelected " + position);
                    tabHost.setSelectedNavigationItem(position);
                    onTabsChange(position);
                }
            });


          //  getSupportFragmentManager().addOnBackStackChangedListener(this);


            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.floating_action_button, null);
            FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                    .setContentView(imageView)
                    .build();
            // actionButton.setBackgroundColor(R.color.accent);
            // actionButton.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.accent_pressed)));


            ImageView itemIcon = new ImageView(this);
            itemIcon.setImageResource(R.drawable.ic_camera_white_24dp);
            ImageView itemIcon2 = new ImageView(this);
            itemIcon2.setImageResource(R.drawable.ic_access_time_white_24dp);

            SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
            SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
            SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = adapter.getItem(viewPager.getCurrentItem());
                    Toast.makeText(MainActivity.this, "Button1 clicked", Toast.LENGTH_SHORT).show();
                    //Fragment fragment=adapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                    //if(fragment instanceof OnSortedListener){}

                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = adapter.getItem(viewPager.getCurrentItem());
                    Toast.makeText(MainActivity.this, "Button2 clicked", Toast.LENGTH_SHORT).show();
                }
            });

            FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                    .addSubActionView(button1)
                    .addSubActionView(button2)
                    .attachTo(actionButton)
                    .build();

        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        try {
            switch (item.getItemId()) {

//                case android.R.id.home: {
//                    Log.e("MealPlanner", "home button clicked");
//                    //listFragment.toggleShowItemAction();
//                    //invalidateOptionsMenu();
//                    // NavUtils.navigateUpFromSameTask(this);
//                    break;
//                }
                case R.id.help: {
                    Toast.makeText(MainActivity.this, "in help menu ", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(this, ActivitySub.class));
                    break;
                }
//                case R.id.action_search: {
//
//                    break;
//                }
                default:

            }
            return super.onOptionsItemSelected(item);
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e) + "\nandroid.R.id.home : " + android.R.id.home);
            return true;
        }
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        try {
            Log.d("MealPlanner", "in onTabSelected");
            viewPager.setCurrentItem(materialTab.getPosition());
            onTabsChange(materialTab.getPosition());
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }


    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        try {
            Log.d("MealPlanner", "in saveToPreferences preferenceValue=" + preferenceValue);
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(preferenceName, preferenceValue);
            //editor.commit(); cf Mat Des Nav Drawer part3
            editor.apply();
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }


    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
            Log.d("MealPlanner", "in readFromPreferences preferenceValue=" + sharedPreferences.getString(preferenceName, defaultValue));
            return sharedPreferences.getString(preferenceName, defaultValue);
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }







    /*
    * EVENTS
     */

    private void onTabsChange(int position) {
        saveToPreferences(this, "selectedTab", String.valueOf(position));
    }


    public Fragment onChangeTabsFragment(int position) {
        try {
            onTabsChange(position);
            switch (position) {

                case 0: {
                    return tabPeriodSelected();
                }
                case 1: {
                    return tabPlanSelected();
                }
                case 2: {
                    return tabDaysSelected();
                }
                default:
                    return null;
            }
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }

    private Fragment tabDaysSelected() {
        try {
            Double quantity = 1000.0; //quantit� nourriture mang�e par jour
            List<Food> bigList = foodDAO.getAllDaysFull(quantity);
            for (int i = 0; i < 5; i++) {
                bigList.addAll(bigList);
            }

            if (bigList.isEmpty()) return EmptyListFragment.getInstance(R.string.days);
            else {
                ListFragment.StateAndBehaviour dayList = new DayList(bigList);
                listFragment = ListFragment.getInstance(dayList);
                return listFragment;
            }
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }

    private Fragment tabPlanSelected() {
        return EmptyListFragment.getInstance(R.string.plan);
    }

    private Fragment tabPeriodSelected() {
        return EmptyListFragment.getInstance(R.string.periods);
    }

//    @Override
//    public void onBackStackChanged() {
//        try{
//        shouldDisplayHomeUp();
//        } catch (Exception e) {
//            Log.e("MealPlanner", Log.getStackTraceString(e));
//        }
//    }
//
//    public void shouldDisplayHomeUp() {
//        try {
//            //Affiche backstatck
//            Log.d("MealPlanner", "in onBackStackChanged");
//            Log.d("MealPlanner", "getBackStackEntryCount()" + this.getSupportFragmentManager().getBackStackEntryCount());
//            for (int i = 0; i < this.getSupportFragmentManager().getBackStackEntryCount(); i++) {
//                Log.d("MealPlanner", "getBackStackEntryCount()" + this.getSupportFragmentManager().getBackStackEntryAt(i));
//            }
//
//
//            //Enable Up button only  if there are entries in the back stack
//            boolean canGoBack = this.getSupportFragmentManager().getBackStackEntryCount() > 0;
//            //this.getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
//            //this.getSupportActionBar().setHomeButtonEnabled(canGoBack);
//            //this.getSupportActionBar().setDisplayShowHomeEnabled(!canGoBack);
//            Log.d("MealPlanner", "canGoBack" + canGoBack);
//
//        } catch (Exception e) {
//            Log.e("MealPlanner", Log.getStackTraceString(e));
//        }
//    }


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
    }

//    @Override
//    public void onBackPressed() {
//        Log.d("MealPlanner", "onBackPressed");
//        listFragment.onBackPressed();
//    }
}
