package com.jxw.git_hub_users.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class GithubUsers implements Parcelable {

    public static final Parcelable.Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {
        public GithubUsers createFromParcel(Parcel in) {
            return new GithubUsers(in);
        }

        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

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


    public GithubUsers(Parcel in) {
        this.userName = in.readString();
        this.imageUrl = in.readString();
        this.profileUrl = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.imageUrl);
        dest.writeString(this.profileUrl);
    }

}
