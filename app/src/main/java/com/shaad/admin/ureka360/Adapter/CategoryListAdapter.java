package com.shaad.admin.ureka360.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaad.admin.ureka360.Model.Category;
import com.shaad.admin.ureka360.R;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CategoryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Category> mList;

    public CategoryListAdapter(Context mContext, List<Category> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = inflater.inflate(R.layout.category_list_row, null);
        }

        CheckedTextView checkedTextView = view.findViewById(R.id.categoty_title);
        checkedTextView.setText(mList.get(i).getName());


        return view;
    }
}
