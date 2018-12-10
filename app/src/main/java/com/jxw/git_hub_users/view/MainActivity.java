package com.jxw.git_hub_users.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.jxw.git_hub_users.R;
import com.jxw.git_hub_users.adapter.GithubAdapter;
import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.presenter.GithubUsersPresenter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements UsersParentView {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.gh_users_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // PRESENTER
        GithubUsersPresenter usersPresenter = new GithubUsersPresenter(this);
        usersPresenter.fetchUsers();
    }

    @Override
    public void getGiHubUsers(List<GithubUsers> githubUsersList) {
        GithubAdapter adapter = new GithubAdapter(githubUsersList, this);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Adapter will be instantiated and set here
        mRecyclerView.setAdapter(adapter);

    }

}
