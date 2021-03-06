package com.jxw.git_hub_users.presenter;

import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.model.GithubUsersResponse;
import com.jxw.git_hub_users.service.GithubService;
import com.jxw.git_hub_users.view.UsersParentView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GithubUsersPresenter {
    /* package */ static final String TAG = "GithubUsersPresenter";
    /* package */ final UsersParentView usersView;
    private GithubService githubService;

    public GithubUsersPresenter(UsersParentView view, GithubService githubService) {
        this.usersView = view;

        if (this.githubService == null) {
            this.githubService = githubService;
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
                        usersView.showErrorMessage(t.getMessage());
                    }
                });

    }
}
