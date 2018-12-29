package com.jxw.git_hub_users;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import com.jxw.git_hub_users.model.GithubUsers;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class GithubUserParcelTest {

    @Test
    public void testParcelable() {
        String username = "john_doe";
        String imageUrl = "http://images.com/john.png";

        GithubUsers user = new GithubUsers(username, imageUrl);
        Parcel parcel = Parcel.obtain();

        user.writeToParcel(parcel, user.describeContents());
        parcel.setDataPosition(0);
        GithubUsers getFromParcel = GithubUsers.CREATOR.createFromParcel(parcel);

        assertThat(getFromParcel.getUserName(), is(username));
        assertThat(getFromParcel.getImageUrl(), is(imageUrl));
    }
}
