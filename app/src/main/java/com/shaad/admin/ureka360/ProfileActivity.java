package com.shaad.admin.ureka360;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shaad.admin.ureka360.Adapter.BottomSheetRecycler;
import com.shaad.admin.ureka360.Adapter.PostRecyclerAdapter;
import com.shaad.admin.ureka360.Helper.RestClient;
import com.shaad.admin.ureka360.Helper.RestInterface;
import com.shaad.admin.ureka360.Model.Option;
import com.shaad.admin.ureka360.Model.Post;
import com.shaad.admin.ureka360.Model.WPMedia;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements WaveSwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ProfileActivity";
    private String name;
    private RecyclerView recyclerView;
    private List<Post> mPostList;
    private PostRecyclerAdapter adapter;
    private WaveSwipeRefreshLayout refreshLayout;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getMetaData();
        setUpToolBar();

        mProgress = findViewById(R.id.progress);
        mProgress.setVisibility(View.VISIBLE);


        refreshLayout = findViewById(R.id.main_swipe);
        recyclerView = findViewById(R.id.list);

        refreshLayout.setOnRefreshListener(this);
        getYourPostAsync();
        mPostList = new ArrayList<>();
        adapter = new PostRecyclerAdapter(this, mPostList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

    }

    private void getYourPostAsync() {
        new FetchCategoryPost().execute();
    }

    @Override
    public void onRefresh() {
        getYourPostAsync();
    }


    class FetchCategoryPost extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            refreshLayout.setRefreshing(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RestInterface restInterface = RestClient.getClient().create(RestInterface.class);
            Intent intent = getIntent();
            int ids = intent.getIntExtra("queryId",0);
            Log.d(TAG, "doInBackground:  = "+ids);
            List<Integer> id = new ArrayList<>();
            id.add(ids);
            Log.d(TAG, "doInBackground: category list size = "+id.size());
            Call<List<Post>> call =restInterface.getBlogPostByCategory(id);

            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    List<Post> posts = response.body();

                    mPostList.clear();
                    mPostList.addAll(posts);
                    mProgress.setVisibility(View.GONE);
                    if(mPostList.isEmpty()){
                        Toast.makeText(ProfileActivity.this, "No Post to load at the moment.", Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                }
            });
            return null;
        }
    }

    private void getMetaData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("queryName");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option:
                List<Option> mBottomList = new ArrayList<>();
                BottomSheetRecycler adapter = new BottomSheetRecycler(ProfileActivity.this, mBottomList);
                mBottomList.add(new Option(R.drawable.ic_save, "Save"));
                mBottomList.add(new Option(R.drawable.ic_share, "Share"));
                mBottomList.add(new Option(R.drawable.ic_fresh, "Block"));
                adapter.notifyDataSetChanged();

                LayoutInflater inflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                View myView = inflater.inflate(R.layout.bottom_sheet_post_option, null);
                RecyclerView recyclerView = myView.findViewById(R.id.sheet_recycler);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(adapter);

                BottomSheetDialog dialog = new BottomSheetDialog(this);
                dialog.setContentView(myView);
                dialog.show();


                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, "setUpToolBar:  = "+name);
        mActionBar.setTitle(name);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);

    }

}
