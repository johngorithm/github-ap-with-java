package com.jxw.git_hub_users;

import android.util.Log;

import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.model.GithubUsersResponse;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

public class GithubUsersResponseTest {
    private GithubUsersResponse githubUsersResponse = new GithubUsersResponse();
    private List<GithubUsers> usersList = new ArrayList<>();
    private GithubUsers userOne = new GithubUsers("john_doe", "http://images.com/john_doe.png");
    private GithubUsers userTwo = new GithubUsers("will_smith", "http://images.com/will_smith.png");

    private static final String TAG = "GithubUsersResponseTest";


    @Test
    public void testGithubUsersResponse() {

        usersList.add(userOne);
        usersList.add(userTwo);

        githubUsersResponse.setUsers(usersList);
        assertEquals(usersList, githubUsersResponse.getUsers());
    }
}
