package com.uramonk.androidtemplateapp.viewmodel;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;
import com.squareup.spoon.Spoon;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.view.MainActivity;

import org.junit.Assert;
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
        solo.waitForFragmentById(R.id.container);
    }

    @Test
    public void changeTextStringWhenClickButton() {
        Spoon.screenshot(activityTestRule.getActivity(), "start_1");
        if(solo.searchButton("OK", true)) {
            solo.clickOnButton("OK");
        }

        Assert.assertTrue("wrong text 1", solo.searchText(""));
        Spoon.screenshot(activityTestRule.getActivity(), "start_2");

        solo.clickOnView(solo.getCurrentActivity().findViewById(R.id.main_fragment_button));
        Assert.assertTrue("wrong text 2", solo.searchText("Button Clicked!"));
        Spoon.screenshot(activityTestRule.getActivity(), "button_clicked_1");

        solo.clickOnView(solo.getCurrentActivity().findViewById(R.id.main_fragment_button));
        Assert.assertTrue("wrong text 3", solo.searchText(""));
        Spoon.screenshot(activityTestRule.getActivity(), "button_clicked_2");
    }
}