package com.jxw.git_hub_users;


import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.model.GithubUsersResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GithubUsersResponseTest {
    private GithubUsersResponse githubUsersResponse = new GithubUsersResponse();
    private List<GithubUsers> usersList = new ArrayList<>();
    private GithubUsers userOne = new GithubUsers("john_doe", "http://images.com/john_doe.png");
    private GithubUsers userTwo = new GithubUsers("will_smith", "http://images.com/will_smith.png");



    @Test
    public void testGithubUsersResponse() {

        usersList.add(userOne);
        usersList.add(userTwo);

        githubUsersResponse.setUsers(usersList);
        assertEquals(usersList, githubUsersResponse.getUsers());
    }
}
