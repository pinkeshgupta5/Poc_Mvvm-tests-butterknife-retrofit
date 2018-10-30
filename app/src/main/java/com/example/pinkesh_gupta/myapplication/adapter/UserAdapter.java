package com.example.pinkesh_gupta.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pinkesh_gupta.myapplication.R;
import com.example.pinkesh_gupta.myapplication.model.UserContent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
   private Context ctx;
   private List<UserContent> userContentList;

    public UserAdapter(Context ctx, List<UserContent> userContentList) {
        this.ctx = ctx;
        this.userContentList = userContentList;
    }
    public void clearUsers() {
        int size = this. userContentList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                userContentList.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    public void addUsers(List<UserContent> userContentList) {
        this.userContentList.addAll(userContentList);
        this.notifyItemRangeInserted(0, userContentList.size() - 1);
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
        if(userContent.getDescription()!=null || userContent.getTitle()!=null)
        {
            holder.cardView.setVisibility(View.VISIBLE);
            holder.imageView_imagearrow.setVisibility(View.VISIBLE);
        }
        else{
            holder.cardView.setVisibility(View.GONE);
            holder.imageView_imagearrow.setVisibility(View.GONE);
        }

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

        @BindView(R.id.card_view)
        CardView cardView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
