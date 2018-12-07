package com.jxw.git_hub_users.model;
import com.google.gson.annotations.SerializedName;


public class GithubUsers {
    @SerializedName("login")
    private String userName;

    @SerializedName("avatar_url")
    private String imageUrl;

    @SerializedName("url")
    private  String profileUrl;

    public GithubUsers(String userName, String imageUrl, String profileUrl) {
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.profileUrl = profileUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
