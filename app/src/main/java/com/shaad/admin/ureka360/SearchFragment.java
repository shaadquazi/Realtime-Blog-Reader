package com.shaad.admin.ureka360;

import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shaad.admin.ureka360.Adapter.PostRecyclerAdapter;
import com.shaad.admin.ureka360.Adapter.SearchRecyclerAdapter;
import com.shaad.admin.ureka360.Helper.RestClient;
import com.shaad.admin.ureka360.Helper.RestInterface;
import com.shaad.admin.ureka360.Model.Category;
import com.shaad.admin.ureka360.Model.Post;
import com.shaad.admin.ureka360.Model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    public static final String TOPIC_NAME = "categoryName";
    private static String[] SUGGESTIONS;
    private SimpleCursorAdapter mAdapter;
    private SearchView mSearchView;
    private List<Category> mCategory;
    private List<User> mUser;
    private List<String> mSuggestionList;
    private RecyclerView recyclerView;
    List<Category> categoryList;
    private SearchRecyclerAdapter adapter;
    private ProgressBar mProgress;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        new LoadHint().execute();

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        mProgress = view.findViewById(R.id.progress);
        mProgress.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.search_list);
        categoryList = new ArrayList<>();
        adapter = new SearchRecyclerAdapter(getContext(), categoryList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                mSearchView = (SearchView) item.getActionView();
                mSearchView.setQueryHint("Search");


                mSearchView.setSuggestionsAdapter(mAdapter);
                mSearchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                    @Override
                    public boolean onSuggestionClick(int position) {
                        mSearchView.onActionViewExpanded();
                        MatrixCursor cursor = (MatrixCursor) mSearchView.getSuggestionsAdapter().getCursor();
                        String select = cursor.getString(1);
                        mSearchView.setQuery(select,Boolean.TRUE);
                        mSearchView.clearFocus();
                        return true;
                    }

                    @Override
                    public boolean onSuggestionSelect(int position) {
                        return true;
                    }
                });

                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
                        intent.putExtra("queryName", query);
                        startActivity(intent);
                        mSearchView.setQuery("",Boolean.TRUE);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        giveSuggestions(newText);
                        return true;
                    }
                });
                return true;

            default:
                // do more stuff
                return true;
        }
    }

    public class LoadHint extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mSuggestionList = new ArrayList<>();
            for(User user:mUser){
                mSuggestionList.add(user.getName());
            }
            for(Category category:mCategory){
                mSuggestionList.add(category.getName());
            }


            mProgress.setVisibility(View.GONE);
            categoryList.clear();
            categoryList.addAll(mCategory);
            adapter.notifyDataSetChanged();
            SUGGESTIONS = mSuggestionList.toArray(new String[0]);

            final String[] from = new String[]{TOPIC_NAME};
            final int[] to = new int[]{android.R.id.text1};
            mAdapter = new SimpleCursorAdapter(Objects.requireNonNull(getActivity()),
                    R.layout.hint_row,
                    null,
                    from,
                    to,
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            RestInterface restInterface = RestClient.getClient().create(RestInterface.class);
            Call<List<Category>> callCategory = restInterface.getCategories();
            Call<List<User>> callUser = restInterface.getUsers();

            mUser = new ArrayList<>();
            mCategory = new ArrayList<>();
            try {
                mCategory.clear();
                mUser.clear();
                mCategory = callCategory.execute().body();
                mUser = callUser.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    private void giveSuggestions(String query) {
        final MatrixCursor cursor = new MatrixCursor(new String[]{BaseColumns._ID, TOPIC_NAME});
        if(SUGGESTIONS != null){
            for (int i = 0; i < SUGGESTIONS.length; i++) {
                if (SUGGESTIONS[i].toLowerCase().contains(query.toLowerCase()))
                    cursor.addRow(new Object[]{i, SUGGESTIONS[i]});
            }
        }
        if(mAdapter != null){
            mAdapter.changeCursor(cursor);
        }
    }

}
