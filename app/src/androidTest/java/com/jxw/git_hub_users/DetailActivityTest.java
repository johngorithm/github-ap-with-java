package com.jxw.git_hub_users;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.jxw.git_hub_users.view.DetailViewActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.constraint.Constraints.TAG;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailViewActivity> detailViewActivityTestRule = new ActivityTestRule<>(DetailViewActivity.class, false, false);

    @Before
    public void launch() {
        Intent intent = new Intent();
        intent.putExtra("userName", "k33ptoo");
        detailViewActivityTestRule.launchActivity(intent);
        sleep(8000);
    }

    @Test
    public void testSuccessfulLaunch() {
        onView(withId(R.id.user_detail_view)).check(matches(isDisplayed()));
        onView(withId(R.id.username_value)).check(matches(withText("k33ptoo")));
    }

    @Test
    public void testSwipeToRefresh() {
        onView(withId(R.id.user_detail_view))
                .perform(ViewActions.swipeDown());
    }

    @Test
    public void testConfigurationChanges() {
        detailViewActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sleep(3000);
        detailViewActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
    }


    public void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            Log.e(TAG, "Thread Error: " + e.getMessage());
        }
    }
}
