package com.shaad.admin.ureka360;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shaad.admin.ureka360.Adapter.CategoryListAdapter;
import com.shaad.admin.ureka360.Helper.RestClient;
import com.shaad.admin.ureka360.Helper.RestInterface;
import com.shaad.admin.ureka360.Model.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCategory extends AppCompatActivity {

    private static final String TAG = "SelectCategory";
    private Button mSubmit;
    private ProgressBar mProgress;
    private ListView listView;
    private List<Category> mCategoryList = new ArrayList<>();
    private List<Integer> mCategoryIds = new ArrayList<>();
    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        mSubmit = findViewById(R.id.select_topic);
        mProgress = findViewById(R.id.progress);
        listView = findViewById(R.id.category_list);
        mProgress.setVisibility(View.VISIBLE);
        mSubmit.setEnabled(false);

        new TaskCategory().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = view.findViewById(R.id.categoty_title);
                if(listView.isItemChecked(i)){
                    count++;
                    checkedTextView.toggle();
                }else {
                    count--;
                    checkedTextView.toggle();
                }
                checkCount();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int len = listView.getCount();
                SparseBooleanArray checked = listView.getCheckedItemPositions();
                for (int i = 0; i < len; i++){
                    if (checked.get(i)) {
                        Category item = mCategoryList.get(i);
                        mCategoryIds.add(item.getId());
                    }
                }

                Intent intent = new Intent(SelectCategory.this, DashBoardActivity.class);
                intent.putExtra("mCategoryIds", (Serializable) mCategoryIds);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        count = 0;
    }

    private void checkCount() {
        Log.d(TAG, "checkCount: "+count);
        if(count > 2){
            mSubmit.setEnabled(true);
        }else{
            mSubmit.setEnabled(false);
        }
    }

    class TaskCategory extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            RestInterface restInterface = RestClient.getClient().create(RestInterface.class);
            Call<List<Category>> call =restInterface.getCategories();

            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if(response.isSuccessful()){
                        mCategoryList = response.body();
                        Log.d(TAG, "onResponse: Done "+mCategoryList.size());
                        mProgress.setVisibility(View.GONE);

                        CategoryListAdapter adapter = new CategoryListAdapter(SelectCategory.this, mCategoryList);
                        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                        listView.setItemsCanFocus(false);
                        listView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    Log.d(TAG, "onFailure: Failed "+t.toString());
                }
            });

            return null;
        }
    }

}
