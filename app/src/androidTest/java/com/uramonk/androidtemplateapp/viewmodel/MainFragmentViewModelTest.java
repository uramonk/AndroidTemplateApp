package com.uramonk.androidtemplateapp.viewmodel;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;
import com.squareup.spoon.Spoon;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.view.MainActivity;
import com.uramonk.androidtemplateapp.view.MainFragment;
import com.uramonk.androidtemplateapp.view.NextFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by uramonk on 2016/07/03.
 */
public class MainFragmentViewModelTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
        solo.waitForActivity(MainActivity.class);
        solo.waitForFragmentByTag(MainFragment.class.getSimpleName());
        solo.unlockScreen();
    }

    @Test
    public void changeTextStringWhenClickButton() {
        Spoon.screenshot(activityTestRule.getActivity(), "start_1");
        if (solo.searchButton("OK", true)) {
            solo.clickOnButton("OK");
        }

        Espresso.onView(ViewMatchers.withId((R.id.main_fragment_textview)))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));
        Spoon.screenshot(activityTestRule.getActivity(), "start_2");

        Espresso.onView(ViewMatchers.withId(R.id.main_fragment_button))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId((R.id.main_fragment_textview)))
                .check(ViewAssertions.matches(ViewMatchers.withText("Button Clicked!")));
        Spoon.screenshot(activityTestRule.getActivity(), "button_clicked_1");

        Espresso.onView(ViewMatchers.withId(R.id.main_fragment_button))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId((R.id.main_fragment_textview)))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));
        Spoon.screenshot(activityTestRule.getActivity(), "button_clicked_2");
    }

    @Test
    public void commitNextFragmentWhenButtonClicked() {
        Spoon.screenshot(activityTestRule.getActivity(), "start_1");
        if (solo.searchButton("OK", true)) {
            solo.clickOnButton("OK");
        }

        Espresso.onView(ViewMatchers.withId(R.id.main_fragment_next_button))
                .perform(ViewActions.click());

        solo.waitForFragmentByTag(NextFragment.class.getSimpleName());

        Espresso.onView(ViewMatchers.withId((R.id.next_fragment_textview)))
                .check(ViewAssertions.matches(ViewMatchers.withText("Next Fragment")));
    }
}