package com.jxw.git_hub_users.presenter;


import android.util.Log;

import com.jxw.git_hub_users.model.UserProfile;
import com.jxw.git_hub_users.service.GithubService;
import com.jxw.git_hub_users.view.UserDetailParentView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserProfilePresenter {
    /* package */ static final String TAG = "UserProfilePresenter";
    /* package */ final UserDetailParentView userProfileView;
    private GithubService githubService;

    public UserProfilePresenter(UserDetailParentView view) {
        this.userProfileView = view;

        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    public void getUserProfile(String userName) {
        this.githubService
                .getAPI()
                .fetchProfile(userName)
                .enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                        UserProfile userInfo = response.body();
                        userProfileView.displayUserProfile(userInfo);
                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });

    }
}
