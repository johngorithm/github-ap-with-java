package com.jxw.git_hub_users;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;


import com.jxw.git_hub_users.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String TAG = "MAIN_ACTIVITY_TEST";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            Log.e(TAG, "Test SetUp Error: " + e.getMessage());
        }
    }

    @Test
    public void testMainActivityLayout() {
        onView(withId(R.id.main_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerView(){
        onView(ViewMatchers.withId(R.id.gh_users_recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSaveAndRestore() {
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sleep();
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
    }

    @Test
    public void testSwipeToRefresh() {
        onView(withId(R.id.main_view))
                .perform(ViewActions.swipeDown());
        sleep();
    }

    @Test
    public void testClickToRefresh() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText("Refresh"))
                .perform(click());
    }

    @Test
    public void testLaunchDetailActivityOnItemClick() {
        onView(withId(R.id.gh_users_recycler_view)).perform(RecyclerViewActions.scrollToPosition(4));
        onView(withId(R.id.gh_users_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
    }

    public void sleep() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            Log.e(TAG, "Thread Error: " + e.getMessage());
        }
    }

}