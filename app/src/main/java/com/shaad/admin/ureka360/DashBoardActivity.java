package com.shaad.admin.ureka360;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shaad.admin.ureka360.Helper.RestClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {

    private static final String TAG = "DashBoardActivity";
    private List<Integer> mCategoryIds = new ArrayList<>();
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        initCategory();
        
        setUpBottomNavBar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                bottomNavigation.setCurrentItem(0);
                return;
            }
        }).show();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setUpBottomNavBar() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab1, R.drawable.ic_for_you, R.color.tabBg);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab2, R.drawable.ic_fresh, R.color.tabBg);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab3, R.drawable.ic_search, R.color.tabBg);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setColored(true);
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setAccentColor(Color.parseColor("#000000"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        loadFragment(new YouFragment());
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(position == 0){
                    loadFragment(new YouFragment());
                }else  if(position == 1){
                    loadFragment(new FreshFragment());
                }else if(position == 2){
                    loadFragment(new SearchFragment());
                }
                return true;
            }
        });
    }

    private void saveCategory() {
        Log.d(TAG, "saveCategory: SAVING "+mCategoryIds.size());
        String mCategoryIdsString = new Gson().toJson(mCategoryIds);
        SharedPreferences prefs = getSharedPreferences(RestClient.SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("mCategoryIds", mCategoryIdsString);
        editor.apply();

    }

    public List<Integer> getListOfCategoryId(){
        SharedPreferences preferences = getSharedPreferences(RestClient.SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String mCategoryIdsString = preferences.getString("mCategoryIds", null);
        Type type = new TypeToken< List < Integer >>() {}.getType();
        List<Integer> mIds = new Gson().fromJson(mCategoryIdsString, type);
        return mIds;
    }

    private void initCategory() {
        Intent intent = getIntent();
        if(intent.hasExtra("mCategoryIds")){
            List<Integer> mTopicList = (List<Integer>) intent.getSerializableExtra("mCategoryIds");
            mCategoryIds.addAll(mTopicList);
            saveCategory();
        }else {
            Log.d(TAG, "initCategory: intent is null ");
        }
    }
}
