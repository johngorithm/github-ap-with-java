package com.jxw.git_hub_users;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.jxw.git_hub_users.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String TAG = "MAIN_ACTIVITY_TEST";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        sleep(5000);
    }

    @Test
    public void testMainActivityLayout() {
        onView(withId(R.id.main_view))
                .check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.gh_users_recycler_view))
                .check(matches(isDisplayed()));
    }


    @Test
    public void testSaveAndRestore() {
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sleep(1000);
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
    }

    @Test
    public void testSwipeToRefresh() {
        onView(withId(R.id.main_view))
                .perform(ViewActions.swipeDown());
        sleep(5000);
        onView(ViewMatchers.withId(R.id.gh_users_recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testClickToRefresh() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText("Refresh"))
                .perform(click());
        sleep(5000);
        onView(ViewMatchers.withId(R.id.gh_users_recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLaunchDetailActivityOnItemClick() {
        onView(withId(R.id.gh_users_recycler_view)).perform(RecyclerViewActions.scrollToPosition(10));
        onView(withId(R.id.gh_users_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        sleep(5000);
    }

    @Test
    public void testLaunchDetailActivityOnItemClick2() {
        onView(withId(R.id.gh_users_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        sleep(5000);
    }

    public void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            Log.e(TAG, "Thread Error: " + e.getMessage());
        }
    }

}