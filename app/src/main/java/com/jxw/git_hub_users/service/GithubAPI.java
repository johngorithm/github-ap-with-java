package com.jxw.git_hub_users.service;

import com.jxw.git_hub_users.model.GithubUsersResponse;
import com.jxw.git_hub_users.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubAPI {
    @GET("/search/users?q=+language:java+location:nairobi&per_page=100")
    Call<GithubUsersResponse> getUsers();

    @GET("/users/{username}")
    Call<UserProfile> fetchProfile(@Path("username") String userName);
}
