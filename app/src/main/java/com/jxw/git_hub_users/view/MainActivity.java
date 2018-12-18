package com.jxw.git_hub_users.view;

import android.app.ProgressDialog;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.jxw.git_hub_users.R;
import com.jxw.git_hub_users.adapter.GithubAdapter;
import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.presenter.GithubUsersPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements UsersParentView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    public static String USERS_KEY = "users";
    public static String RECYCLER_VIEW_KEY = "viewKey";
    public List<GithubUsers> ghUsers;
    GridLayoutManager mLayoutManager;
    Parcelable recyclerViewParcelable;
    ProgressDialog progressDialog;
    GithubUsersPresenter usersPresenter;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.gh_users_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorAccent
        );


        if (savedInstanceState != null) {
            this.ghUsers = savedInstanceState.getParcelableArrayList(USERS_KEY);
            this.displayUsers(ghUsers);
        } else  {
            // PRESENTER
            usersPresenter = new GithubUsersPresenter(this);
            usersPresenter.fetchUsers();
        }

        Log.i(TAG, "ON-CREATE IS CALLED");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.menu_refresh == item.getItemId()) {
            swipeRefreshLayout.setRefreshing(true);
            usersPresenter.fetchUsers();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (recyclerViewParcelable != null) {
            mLayoutManager.onRestoreInstanceState(recyclerViewParcelable);
        }
        Log.i(TAG, "ON-RESUME IS CALLED");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(USERS_KEY, (ArrayList<GithubUsers>) this.ghUsers);
        this.recyclerViewParcelable = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(RECYCLER_VIEW_KEY, recyclerViewParcelable);

        Log.i(TAG, "ON-SAVE-INSTANCE-STATE IS CALLED");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            recyclerViewParcelable = savedInstanceState.getParcelable(RECYCLER_VIEW_KEY);
        }
        Log.i(TAG, "ON-RESTORE-INSTANCE-STATE IS CALLED");
    }

    @Override
    public void displayUsers(List<GithubUsers> githubUsersList) {
        progressDialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);

        ghUsers = githubUsersList;
        GithubAdapter adapter = new GithubAdapter(githubUsersList, this);
        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Adapter will be instantiated and set here
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        // FETCH USERS
        usersPresenter.fetchUsers();
    }
}
