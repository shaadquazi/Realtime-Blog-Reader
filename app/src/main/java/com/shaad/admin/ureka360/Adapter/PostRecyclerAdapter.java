package com.shaad.admin.ureka360.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaad.admin.ureka360.Helper.RestClient;
import com.shaad.admin.ureka360.Helper.RestInterface;
import com.shaad.admin.ureka360.Model.Option;
import com.shaad.admin.ureka360.Model.Post;
import com.shaad.admin.ureka360.Model.WPMedia;
import com.shaad.admin.ureka360.R;
import com.shaad.admin.ureka360.ReadActivity;
import com.squareup.picasso.Picasso;
import com.thefinestartist.finestwebview.FinestWebView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.MyViewHolder> {

    private static final String TAG = "PostRecyclerAdapter";
    private Context mContext;
    private List<Post> mList;

    public PostRecyclerAdapter(Context mContext, List<Post> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public PostRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostRecyclerAdapter.MyViewHolder holder, int position) {
        Post post = mList.get(position);

        RestInterface restInterface = RestClient.getClient().create(RestInterface.class);
        Call<WPMedia> call = restInterface.getMediaById(post.getFeatured_media());
        call.enqueue(new Callback<WPMedia>() {
            @Override
            public void onResponse(Call<WPMedia> call, Response<WPMedia> response) {
                WPMedia media = response.body();
                if(media != null){
                    Picasso.get().load(media.getGuid().getRendered()).into(holder.mThumbnail);
                    Log.d(TAG, "onBindViewHolder: loading image");
                }else {
                    holder.mThumbnail.setImageResource(R.drawable.ic_failed);
                    Log.d(TAG, "onBindViewHolder: media null at = "+holder.getAdapterPosition());
                }
            }

            @Override
            public void onFailure(Call<WPMedia> call, Throwable t) {
                holder.mThumbnail.setImageResource(R.drawable.ic_failed);
                Log.d(TAG, "onFailure: could not set thumbnail");
            }
        });

        holder.mTitle.setText(post.getTitle().getRendered());

        SimpleDateFormat input = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy");

        //output.format(input.parse(post.getDate()))

        String by_author = "By: "+post.getAuthor();
        holder.mMetaData.setText(by_author);



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mThumbnail;
        private TextView mTitle, mMetaData, mOption;
        private ConstraintLayout layout;
        private List<Option> mBottomList;
        private BottomSheetRecycler adapter;

        public MyViewHolder(View itemView) {
            super(itemView);
            mThumbnail = itemView.findViewById(R.id.post_photo);
            mTitle = itemView.findViewById(R.id.post_name);
            mMetaData = itemView.findViewById(R.id.post_meta_data);
            mOption = itemView.findViewById(R.id.post_option);
            layout = itemView.findViewById(R.id.post);
            layout.setOnClickListener(this);
            mOption.setOnClickListener(this);

        }

        public void getBottomString(){
            mBottomList = new ArrayList<>();
            adapter = new BottomSheetRecycler(mContext, mBottomList);

            mBottomList.add(new Option(R.drawable.ic_save, "Save"));
            mBottomList.add(new Option(R.drawable.ic_share, "Share"));
            mBottomList.add(new Option(R.drawable.ic_fresh, "Block"));

            adapter.notifyDataSetChanged();
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.post:


                    //Intent intent = new Intent(mContext, ReadActivity.class);
                    //mContext.startActivity(intent);

                    new FinestWebView.Builder(mContext).show(mList.get(getAdapterPosition()).getLink());





                    break;
                case R.id.post_option:

                    getBottomString();

                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    View myView = inflater.inflate(R.layout.bottom_sheet_post_option, null);
                    RecyclerView recyclerView = myView.findViewById(R.id.sheet_recycler);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(adapter);

                    BottomSheetDialog dialog = new BottomSheetDialog(mContext);
                    dialog.setContentView(myView);
                    dialog.show();

                    break;
            }
        }
    }

}
