package com.shaad.admin.ureka360;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shaad.admin.ureka360.Helper.RestClient;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class GetStarted extends AppCompatActivity {

    private static final String TAG = "GetStarted";
    private Button mGetStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        mGetStartedBtn = findViewById(R.id.get_started_btn);
        mGetStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> mListId = getListOfCategoryId();
                try{
                    Log.d(TAG, "onClick: "+mListId.size());
                    startActivity(new Intent(GetStarted.this, DashBoardActivity.class));
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                    startActivity(new Intent(GetStarted.this, SelectCategory.class));
                }
                finish();






            }
        });

    }


    public List<Integer> getListOfCategoryId(){
        SharedPreferences preferences = getSharedPreferences(RestClient.SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String mCategoryIdsString = preferences.getString("mCategoryIds", null);
        Type type = new TypeToken< List < Integer >>() {}.getType();
        List<Integer> mIds = new Gson().fromJson(mCategoryIdsString, type);
        return mIds;
    }
}
