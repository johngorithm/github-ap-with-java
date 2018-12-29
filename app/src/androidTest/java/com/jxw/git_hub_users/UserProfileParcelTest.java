package com.jxw.git_hub_users;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import com.jxw.git_hub_users.model.UserProfile;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class UserProfileParcelTest {

    @Test
    public void testUserProfileParcel () {
        String username = "john_doe";
        String imageUrl = "http://images.com/john.png";
        String following = "53";
        String followers = "20";
        String company = "Switch";
        String publicRepos = "118";
        String bio = "";

        Parcel parcel = Parcel.obtain();

        UserProfile userProfile = new UserProfile(
                imageUrl,
                followers,
                following,
                username,
                company,
                publicRepos,
                bio
        );

        userProfile.writeToParcel(parcel, userProfile.describeContents());
        parcel.setDataPosition(0);
        UserProfile getProfileFromParcel = UserProfile.CREATOR.createFromParcel(parcel);

        assertThat(getProfileFromParcel.getImageUrl(), is(imageUrl));
        assertThat(getProfileFromParcel.getFollowers(), is(followers));
        assertThat(getProfileFromParcel.getFollowing(), is(following));
        assertThat(getProfileFromParcel.getUserName(), is(username));
        assertThat(getProfileFromParcel.getCompany(), is(company));
        assertThat(getProfileFromParcel.getPublicRepos(), is(publicRepos));
        assertThat(getProfileFromParcel.getBio(), is(bio));
    }
}
