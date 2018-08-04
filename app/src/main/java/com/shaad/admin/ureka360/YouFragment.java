package com.shaad.admin.ureka360;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shaad.admin.ureka360.Adapter.PostRecyclerAdapter;
import com.shaad.admin.ureka360.Helper.RestClient;
import com.shaad.admin.ureka360.Helper.RestInterface;
import com.shaad.admin.ureka360.Model.Post;
import com.shaad.admin.ureka360.Model.WPMedia;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class YouFragment extends Fragment implements WaveSwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "YouFragment";
    private RecyclerView recyclerView;
    private List<Post> mPostList;
    private List<WPMedia> mWPList;
    private PostRecyclerAdapter adapter;
    private WaveSwipeRefreshLayout refreshLayout;
    private ProgressBar mProgress;

    public YouFragment() {
        // Required empty public constructor
    }

    public static YouFragment newInstance(String param1, String param2) {
        YouFragment fragment = new YouFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_you, container, false);
        recyclerView = view.findViewById(R.id.main_list);
        refreshLayout = view.findViewById(R.id.main_swipe);

        mProgress = view.findViewById(R.id.progress);
        mProgress.setVisibility(View.VISIBLE);

        refreshLayout.setOnRefreshListener(this);
        getYourPostAsync();
        mPostList = new ArrayList<>();
        mWPList = new ArrayList<>();
        adapter = new PostRecyclerAdapter(getContext(), mPostList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        return view;
    }


    @Override
    public void onRefresh() {
        getYourPostAsync();
    }

    private void getYourPostAsync() {
        new FetchCategoryPost().execute();
    }

    class FetchCategoryPost extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            refreshLayout.setRefreshing(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RestInterface restInterface = RestClient.getClient().create(RestInterface.class);
            List<Integer> id = getListOfCategoryId();
            Call<List<Post>> call =restInterface.getBlogPostByCategory(id);
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    List<Post> posts = response.body();

                    mPostList.clear();
                    mPostList.addAll(posts);
                    adapter.notifyDataSetChanged();
                    mProgress.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                }
            });
            return null;
        }
    }

    public List<Integer> getListOfCategoryId(){
        SharedPreferences preferences = getActivity().getSharedPreferences(RestClient.SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String mCategoryIdsString = preferences.getString("mCategoryIds", null);
        Type type = new TypeToken< List < Integer >>() {}.getType();
        List<Integer> mIds = new Gson().fromJson(mCategoryIdsString, type);
        return mIds;
    }


}
