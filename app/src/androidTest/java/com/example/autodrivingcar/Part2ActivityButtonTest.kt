package com.example.autodrivingcar

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class Part2ActivityButtonTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(Part2Activity::class.java)

    @Test
    fun testBtnBackToMain1Button() {
        Espresso.onView(ViewMatchers.withId(R.id.btnBackToMain2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText(R.string.app_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}