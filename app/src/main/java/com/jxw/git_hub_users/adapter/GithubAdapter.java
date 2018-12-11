package com.jxw.git_hub_users.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jxw.git_hub_users.R;
import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.view.DetailViewActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.UserViewHolder> {

    private final List<GithubUsers> users;
    /* package */ final Context context;

    public GithubAdapter(List<GithubUsers> githubUsersList, Context mContext) {
        this.users = githubUsersList;
        this.context = mContext;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        // Get the data model based on position
        GithubUsers user = users.get(position);

        // Set item views based on your views and data model
        holder.username.setText(user.getUserName());

        Glide.with(context)
                .asBitmap()
                .load(user.getImageUrl())
                .into(holder.profileImage);

        // SET ON CLICK LISTENER
        View.OnClickListener userCardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewDetails = new Intent(context, DetailViewActivity.class);
                context.startActivity(viewDetails);
            }
        };

        holder.userCardLayout.setOnClickListener(userCardClickListener);
    }

    @NonNull
    @Override
    public GithubAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View usersView = inflater.inflate(R.layout.list_item_layout, parent, false);

        // Return a new holder instance
        return  new UserViewHolder(usersView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profileImage;
        TextView username;
        CardView userCardLayout;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.user_image);
            username = itemView.findViewById(R.id.username_value);
            userCardLayout = itemView.findViewById(R.id.user_card);
        }
    }
}
