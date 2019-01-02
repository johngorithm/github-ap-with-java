package com.jxw.git_hub_users;


import com.jxw.git_hub_users.model.GithubUsers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class GithubUsersTest {
    private String userName = "Johngorithm";
    private String imageUrl = "http://fake-image-url.com/photo.png";
    private GithubUsers githubUserModel = new GithubUsers(userName, imageUrl);

    @Test
    public void testGetUsername() {
        assertEquals(userName, githubUserModel.getUserName());
    }

    @Test
    public void testGetImageUrl() {
        assertEquals(imageUrl, githubUserModel.getImageUrl());
    }

    @Test
    public void testSetUserName() {
        final String newUserName = "john_doe_01";
        githubUserModel.setUserName(newUserName);
        assertEquals(githubUserModel.getUserName(), newUserName);
    }
    @Test
    public void testSetImageUrl() {
        final String imageUrl = "http://new-image-url.com/i.png";
        githubUserModel.setImageUrl(imageUrl);
        assertEquals(githubUserModel.getImageUrl(), imageUrl);
    }
}
