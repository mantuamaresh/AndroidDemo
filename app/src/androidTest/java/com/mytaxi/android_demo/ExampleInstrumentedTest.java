package com.mytaxi.android_demo;

import android.content.res.Resources;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;
import com.mytaxi.android_demo.activities.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;

/**
 * Instrumented test, which will perform test execution on Android Device
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private String mStringUserName,mStringPassword,mSearchItem,mSearchKeyword;
    private MainActivity mActivity = null;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * setUp test, which will initialize the android main activity and required global var's
     */
    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        mStringUserName = "whiteelephant261";
        mStringPassword ="video";
        mSearchItem ="sa";
        mSearchKeyword = "Sarah Friedrich";
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        mActivity.runOnUiThread(wakeUpDevice);

    }
    /**
     * mTaxiSearchDriver test, which will perform LogIn operation on MyTaxi application,
     * Search for drivers with provided keyword,
     * Select the driver and make call
     */
    @Test
    public void mTaxiSearchDriver() {
        try {
            onView(withId(R.id.edt_username)).check (matches(isDisplayed())).perform(typeText(mStringUserName), closeSoftKeyboard());
            onView(withId(R.id.edt_username)).check(matches(withText(mStringUserName)));
            onView(withId(R.id.edt_password)).check (matches(isDisplayed())).perform(typeText(mStringPassword), closeSoftKeyboard());
            onView(withId(R.id.btn_login)).check (matches(isDisplayed())).perform(click());
            Thread.sleep(3000);
            onView(withId(R.id.textSearch)).check (matches(isDisplayed())).perform(typeText(mSearchItem), closeSoftKeyboard());
            onView(withText(mSearchKeyword)).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
            onView(withText(mSearchKeyword)).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).perform(click());
            onView(withId(R.id.fab)).check (matches(isDisplayed())).perform(click());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * tearDown test, which will Dispose all the instances after test execution completed
     */
    @After
    public void tearDown(){

    }

}





