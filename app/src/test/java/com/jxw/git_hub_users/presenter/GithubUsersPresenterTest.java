package com.jxw.git_hub_users.presenter;

import com.jxw.git_hub_users.model.GithubUsers;
import com.jxw.git_hub_users.model.GithubUsersResponse;
import com.jxw.git_hub_users.service.GithubService;
import com.jxw.git_hub_users.view.UsersParentView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class GithubUsersPresenterTest {

    private GithubUsersPresenter githubUsersPresenter;
    private List<GithubUsers> githubUsersList;
    private GithubUsersResponse githubUsersResponse;

    @Mock
    UsersParentView usersParentView;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    GithubService githubApiService;

    @Mock
    Call<GithubUsersResponse> mockCall;

    @Mock
    ResponseBody responseBody;

    @Captor
    ArgumentCaptor<Callback<GithubUsersResponse>> argumentCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        githubUsersPresenter = new GithubUsersPresenter(usersParentView, githubApiService);
        GithubUsers user1 = new GithubUsers("john_doe", "http://images.com/john_doe.png");
        GithubUsers user2 = new GithubUsers("jane_doe", "http://images.com/john_doe.png");
        GithubUsers user3 = new GithubUsers("will smith", "http://images.com/john_doe.png");
        githubUsersList = new ArrayList<>();
        githubUsersList.add(user1);
        githubUsersList.add(user2);
        githubUsersList.add(user3);
        githubUsersResponse = new GithubUsersResponse(githubUsersList);
    }

    @Test
    public void test_fetchUsers_success() {
        when(githubApiService.getAPI().getUsers()).thenReturn( mockCall );
        Response<GithubUsersResponse> response = Response.success( githubUsersResponse );

        githubUsersPresenter.fetchUsers();

        verify(mockCall).enqueue( argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, response);
        verify(usersParentView).displayUsers(githubUsersList);
    }

    @Test
    public void test_fetchUsers_failure() {
        when(githubApiService.getAPI().getUsers()).thenReturn( mockCall );
        Throwable throwable = new Throwable( new RuntimeException());

        githubUsersPresenter.fetchUsers();

        verify(mockCall).enqueue( argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(null, throwable);
        verify(usersParentView).showErrorMessage(throwable.getMessage());
    }

    @Test
    public void test_fetchUsers_badRespone() {
        when(githubApiService.getAPI().getUsers()).thenReturn(mockCall);
        Response<GithubUsersResponse> fakeResponse = Response.error(500, responseBody);

        githubUsersPresenter.fetchUsers();

        verify(mockCall).enqueue( argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, fakeResponse);

        verifyZeroInteractions(usersParentView);

    }
}