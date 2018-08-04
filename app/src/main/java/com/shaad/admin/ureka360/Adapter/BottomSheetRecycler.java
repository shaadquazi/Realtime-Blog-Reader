package com.shaad.admin.ureka360.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shaad.admin.ureka360.Helper.RestClient;
import com.shaad.admin.ureka360.Model.Option;
import com.shaad.admin.ureka360.R;

import java.lang.reflect.Type;
import java.util.List;

public class BottomSheetRecycler extends RecyclerView.Adapter<BottomSheetRecycler.MyViewHolder> {

    private static final String TAG = "BottomSheetRecycler";
    private Context mContext;
    private List<Option> mList;

    public BottomSheetRecycler(Context mContext, List<Option> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Option option = mList.get(position);
        holder.mText.setText(option.getTitle());
        holder.mIcon.setImageResource(option.getIconId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mText;
        private ImageView mIcon;
        private RelativeLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.value);
            mIcon = itemView.findViewById(R.id.icon);
            layout = itemView.findViewById(R.id.option);

            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.option){
                switch (getAdapterPosition()){
                    case 0:
                        Toast.makeText(mContext, "Save", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mContext, "Share", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(mContext, "Block", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        }
    }

}
