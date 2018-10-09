package com.example.pinkesh_gupta.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context ctx;
    List<UserContent> userContentList;

    public UserAdapter(Context ctx, List<UserContent> userContentList) {
        this.ctx = ctx;
        this.userContentList = userContentList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.recyclerview, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserContent userContent = userContentList.get(position);
        Glide.with(ctx)
                .load(userContent.getImageHref())
                .into(holder.imageView_imageitem);

        holder.textView_description.setText(userContent.getDescription());
        holder.textView_title.setText(userContent.getTitle());

    }

    @Override
    public int getItemCount() {
        return userContentList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemimage)
        ImageView imageView_imageitem;

        @BindView(R.id.itemdescription)
        TextView textView_description;

        @BindView(R.id.itemtitle)
        TextView textView_title;

        @BindView(R.id.image_arrow)
        ImageView imageView_imagearrow;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
