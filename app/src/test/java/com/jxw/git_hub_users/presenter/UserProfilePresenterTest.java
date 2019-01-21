package com.jxw.git_hub_users.presenter;

import com.jxw.git_hub_users.model.UserProfile;
import com.jxw.git_hub_users.service.GithubService;
import com.jxw.git_hub_users.view.UserDetailParentView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


public class UserProfilePresenterTest {
    private UserProfilePresenter userProfilePresenter;
    private UserProfile userProfile;

//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Call<UserProfile> mockCall;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    GithubService githubApiService;

    @Mock
    UserDetailParentView userDetailParentView;

    @Captor
    ArgumentCaptor<Callback<UserProfile>> responseArgumentCaptor;

    @Captor
    ArgumentCaptor<String> usernameCaptor;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userProfilePresenter = new UserProfilePresenter(userDetailParentView, githubApiService);
        userProfile = new UserProfile();
        userProfile.setBio("I love coding");
        userProfile.setCompany("Jovinx");
        userProfile.setFollowers("897");
        userProfile.setFollowing("65");
        userProfile.setUserName("jovinx");
        userProfile.setImageUrl("http://image.png");
        userProfile.setPublicRepos("670");
    }

    @Test
    public void test_getUserProfile_success() {
        // returning a mock
        when(githubApiService.getAPI().fetchProfile(usernameCaptor.capture())).thenReturn(mockCall);
        // preparing reponse
        Response<UserProfile> response = Response.success(userProfile);
        // making the actual method call
        userProfilePresenter.getUserProfile("jovinx");

        // verifying that d .enqueue method was called and capturing its argument
        verify(mockCall).enqueue(responseArgumentCaptor.capture());
        // calling the onResponse method of the captured argument above
        responseArgumentCaptor.getValue().onResponse(null, response);

        // checking that the view method .displayUserProfile() is called with our API response
        verify(userDetailParentView).displayUserProfile(userProfile);

    }

    @Test
    public void test_getUserProfile_failure() {
        when(githubApiService.getAPI().fetchProfile(usernameCaptor.capture())).thenReturn( mockCall );
        // Response
        Throwable throwable = new Throwable( new RuntimeException());

        // Actual call
        userProfilePresenter.getUserProfile("jxw");

        // verification
        verify(mockCall).enqueue(responseArgumentCaptor.capture());
        // stud and assign appropriate response
        responseArgumentCaptor.getValue().onFailure(null, throwable);
        // verify that no interaction happened with the view
        verifyZeroInteractions(userDetailParentView);
    }
}