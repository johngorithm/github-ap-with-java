package com.jxw.git_hub_users;

import com.jxw.git_hub_users.model.UserProfile;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserProfileTest {
    private final String imageUrl = "http://images.com/user.png";
    private final String followers = "35";
    private final String following = "67";
    private final String userName = "theDancercodes";
    private final String company = "mTech";
    private final String publicRepos = "90";
    private final String bio = "Loving code";


    private UserProfile userProfile = new UserProfile();

    @Test
    public void testUserProfileInstanceWithData() {
        UserProfile profile = new UserProfile(
                imageUrl,
                followers,
                following,
                userName,
                company,
                publicRepos,
                bio
        );
        assertEquals(userName, profile.getUserName());

    }

    @Test
    public void testSetImageUrl() {
        userProfile.setImageUrl(imageUrl);
        assertEquals(imageUrl, userProfile.getImageUrl());
    }

    @Test
    public void testSetUserName() {
        userProfile.setUserName(userName);
        assertEquals(userName, userProfile.getUserName());
    }

    @Test
    public void testSetFollowing() {
        userProfile.setFollowing(following);
        assertEquals(following, userProfile.getFollowing());
    }

    @Test
    public void testSetFollowers() {
        userProfile.setFollowers(followers);
        assertEquals(followers, userProfile.getFollowers());
    }


    @Test
    public void testSetCompany() {
        userProfile.setCompany(company);
        assertEquals(company, userProfile.getCompany());
    }

    @Test
    public void testSetBio() {
        userProfile.setBio(bio);
        assertEquals(bio, userProfile.getBio());
    }

    @Test
    public void testSetPublicRepos() {
        userProfile.setPublicRepos(publicRepos);
        assertEquals(publicRepos, userProfile.getPublicRepos());
    }

    @Test
    public void testDescContent () {
        int val = userProfile.describeContents();
        assertEquals(val, 0);
    }
}
