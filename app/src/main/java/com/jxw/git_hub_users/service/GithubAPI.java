package com.jxw.git_hub_users.service;

import com.jxw.git_hub_users.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GithubAPI {
    @GET("/search/users?q=+language:java+location:nairobi&per_page=100")
    Call<GithubUsersResponse> getUsers();
}
