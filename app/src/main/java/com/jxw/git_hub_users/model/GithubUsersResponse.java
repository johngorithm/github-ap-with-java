package com.jxw.git_hub_users.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUsersResponse {
    @SerializedName("items")
    private List<GithubUsers> users;

    GithubUsersResponse(List<GithubUsers> users) {
        this.users = users;
    }

    public List<GithubUsers> getUsers() {
        return users;
    }

    public void setUsers(List<GithubUsers> users) {
        this.users = users;
    }
}
