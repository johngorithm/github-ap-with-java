package com.jxw.git_hub_users;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.jxw.git_hub_users.view.MainActivity;
import com.linkedin.android.testbutler.TestButler;

import org.junit.After;
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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityNetworkTest {

    private static final String TAG = "MAIN_ACTIVITY_TEST";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        sleep(5000);
        toggleConnectivity(false);
    }

    @Test
    public void testShowsSnackBarOnNoInternetConnection () {
        sleep(1000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText("Refresh"))
                .perform(click());

        onView(allOf(withId(R.id.snackbar_text), withText(R.string.network_failure_message))).check(matches(isDisplayed()));
        sleep(1000);
    }

    @Test
    public void testRefreshOnRetryClick () {
        sleep(1000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText("Refresh"))
                .perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_action)))
                .perform(click());
        sleep(1000);
        onView(allOf(withId(R.id.snackbar_text), withText(R.string.network_failure_message))).check(matches(isDisplayed()));
        sleep(1000);
    }


    public void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            Log.e(TAG, "Thread Error: " + e.getMessage());
        }
    }

    private void toggleConnectivity(boolean enable) {
        TestButler.setGsmState(enable);
        TestButler.setWifiState(enable);
    }

    @After
    public void teardown() {
        toggleConnectivity(true);
    }

}