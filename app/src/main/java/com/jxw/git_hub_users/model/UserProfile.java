package com.jxw.git_hub_users.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserProfile implements Parcelable {
    @SerializedName("avatar_url")
    String imageUrl;

    @SerializedName("followers")
    String followers;

    @SerializedName("following")
    String following;

    @SerializedName("login")
    String userName;

    @SerializedName("company")
    String company;

    @SerializedName("public_repos")
    String publicRepos;

    @SerializedName("bio")
    String bio;

    public static final Parcelable.Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    public UserProfile(Parcel in) {
        this.imageUrl = in.readString();
        this.followers = in.readString();
        this.following = in.readString();
        this.userName = in.readString();
        this.company = in.readString();
        this.publicRepos = in.readString();
        this.bio = in.readString();
    }


    public UserProfile(String imageUrl, String followers, String following, String userName, String company, String publicRepos, String bio) {
        this.imageUrl = imageUrl;
        this.followers = followers;
        this.following = following;
        this.userName = userName;
        this.company = company;
        this.publicRepos = publicRepos;
        this.bio = bio;
    }

    public UserProfile(){} //NOPMD


    public String getImageUrl() {
        return imageUrl;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getUserName() {
        return userName;
    }

    public String getCompany() {
        return company;
    }

    public String getPublicRepos() {
        return publicRepos;
    }

    public String getBio() {
        return bio;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPublicRepos(String publicRepos) {
        this.publicRepos = publicRepos;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeString(this.followers);
        dest.writeString(this.following);
        dest.writeString(this.userName);
        dest.writeString(this.company);
        dest.writeString(this.publicRepos);
        dest.writeString(this.bio);
    }
}
