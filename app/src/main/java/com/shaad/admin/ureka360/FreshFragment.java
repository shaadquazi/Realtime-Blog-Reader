package com.shaad.admin.ureka360;

import android.content.Context;
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

import com.shaad.admin.ureka360.Adapter.PostRecyclerAdapter;
import com.shaad.admin.ureka360.Helper.RestClient;
import com.shaad.admin.ureka360.Helper.RestInterface;
import com.shaad.admin.ureka360.Model.Category;
import com.shaad.admin.ureka360.Model.Post;
import com.shaad.admin.ureka360.Model.WPMedia;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FreshFragment extends Fragment implements WaveSwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "FreshFragment";
    private RecyclerView recyclerView;
    private List<Post> mPostList;
    private List<WPMedia> mWPList;
    private PostRecyclerAdapter adapter;
    private WaveSwipeRefreshLayout refreshLayout;
    private ProgressBar mProgress;

    public FreshFragment() {
        // Required empty public constructor
        //getPostAsync();
    }


    public static FreshFragment newInstance(String param1, String param2) {
        FreshFragment fragment = new FreshFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fresh, container, false);
        recyclerView = view.findViewById(R.id.main_list);
        refreshLayout = view.findViewById(R.id.main_swipe);

        mProgress = view.findViewById(R.id.progress);
        mProgress.setVisibility(View.VISIBLE);

        refreshLayout.setOnRefreshListener(this);
        getPostAsync();
        mPostList = new ArrayList<>();
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
        getPostAsync();
    }

    private void getPostAsync() {
        new FetchPost().execute();
    }


    class FetchPost extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPostExecute(Void aVoid) {
            refreshLayout.setRefreshing(false);
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RestInterface restInterface = RestClient.getClient().create(RestInterface.class);
            Call<List<Post>> call =restInterface.getBlogPost(10);

            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    List<Post> posts = response.body();
                    Log.d(TAG, "onResponse: DONE POST"+mPostList.size());

                    mPostList.clear();
                    mPostList.addAll(posts);
                    adapter.notifyDataSetChanged();
                    mProgress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    Log.d(TAG, "onFailure: "+t.toString());
                }
            });

            return null;
        }
    }

}
