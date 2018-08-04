package com.shaad.admin.ureka360.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shaad.admin.ureka360.Helper.RestClient;
import com.shaad.admin.ureka360.Model.Category;
import com.shaad.admin.ureka360.ProfileActivity;
import com.shaad.admin.ureka360.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.MyViewHolder>{

    private static final String TAG = "SearchRecyclerAdapter";
    private Context mContext;
    private List<Category> mList;
    private List<Integer> integerList;

    public SearchRecyclerAdapter(Context mContext, List<Category> mList) {
        this.mContext = mContext;
        this.mList = mList;
        integerList = getListOfCategoryId();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Category category = mList.get(position);
        holder.mTitle.setText(category.getName());
        Log.d(TAG, "onBindViewHolder: size of id = "+integerList.size());
        for(Integer id:integerList){
            if(id.equals(category.getId())){
                toggleButton(holder.mFollow, category);
            }
        }

        holder.mFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton(holder.mFollow, category);
                Log.d(TAG, "onClick: "+integerList.toString());
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProfileActivity.class);
                intent.putExtra("queryName", category.getName());
                intent.putExtra("queryId", category.getId());
                Log.d(TAG, "onClick:  = "+category.getId());
                mContext.startActivity(intent);
            }
        });

    }

    private void toggleButton(Button mFollow, Category category) {
        if(mFollow.getText().equals("Follow")){
            mFollow.setText("Un Follow");
            if(!integerList.contains(category.getId())){
                integerList.add(category.getId());
            }
        }else {
            if(integerList.size() > 3){
                if(integerList.contains(category.getId())){
                    mFollow.setText("Follow");
                    integerList.remove(category.getId());
                }
            }else {
                Toast.makeText(mContext, "Should follow atleast 3 Topics", Toast.LENGTH_SHORT).show();
            }
        }

        saveCategory(integerList);

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<Integer> getListOfCategoryId(){
        SharedPreferences preferences = mContext.getSharedPreferences(RestClient.SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String mCategoryIdsString = preferences.getString("mCategoryIds", null);
        Type type = new TypeToken< List < Integer >>() {}.getType();
        List<Integer> mIds = new Gson().fromJson(mCategoryIdsString, type);
        return mIds;
    }

    private void saveCategory(List<Integer> mCategoryIds) {
        Log.d(TAG, "saveCategory: SAVING "+mCategoryIds.size());
        String mCategoryIdsString = new Gson().toJson(mCategoryIds);
        SharedPreferences prefs = mContext.getSharedPreferences(RestClient.SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("mCategoryIds", mCategoryIdsString);
        editor.apply();

    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout layout;
        private TextView mTitle;
        private Button mFollow;

        public MyViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.search_layout);
            mTitle = itemView.findViewById(R.id.topic_list);
            mFollow = itemView.findViewById(R.id.follow);
        }

    }

}
