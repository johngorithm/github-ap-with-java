package com.jxw.git_hub_users.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jxw.git_hub_users.R;
import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.presenter.GithubUsersPresenter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements UsersParentView {

    private static final String TAG = "Users List";
    private RecyclerView mRecyclerView;
    private CardView userCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.gh_users_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Adapter will be instantiated and set here
        mRecyclerView.setAdapter(null);

        addCardClickListner();

        // PRESENTER
        GithubUsersPresenter usersPresenter = new GithubUsersPresenter(this);
        usersPresenter.fetchUsers();
    }

    @Override
    public void getGiHubUsers(List<GithubUsers> githubUsersList) {
        Log.d(TAG, ""+githubUsersList.size());
    }


    private void addCardClickListner() {
        userCard = findViewById(R.id.user_card);
        // CREATES AN ONCLICK EVENT LISTENER
        View.OnClickListener userCardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewDetails = new Intent(MainActivity.this, DetailViewActivity.class);
                startActivity(viewDetails);
            }
        };
        // ATTACH THE EVENT LISTENER TO THE USER CARD VIEW
        userCard.setOnClickListener(userCardClickListener);
    }

}
