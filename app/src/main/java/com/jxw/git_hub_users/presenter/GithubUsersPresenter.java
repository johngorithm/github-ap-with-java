package com.jxw.git_hub_users.presenter;


import android.util.Log;

import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.model.GithubUsersResponse;
import com.jxw.git_hub_users.service.GithubService;
import com.jxw.git_hub_users.view.UsersParentView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GithubUsersPresenter {
    private static final String TAG = "GithubUsersPresenter";
    private UsersParentView usersView;
    private GithubService githubService;

    public GithubUsersPresenter(UsersParentView view) {
        this.usersView = view;

        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    public void fetchUsers() {
        this.githubService
                .getAPI()
                .getUsers()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                        GithubUsersResponse data = response.body();

                        if (data != null && data.getUsers() != null) {
                            List<GithubUsers> githubUsers = data.getUsers();
                            usersView.displayUsers(githubUsers);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });

    }
}
