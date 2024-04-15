package com.example.autodrivingcar

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testPartOneButton() {
        onView(withId(R.id.btnPartOne)).perform(click())
        onView(withText(R.string.part_1)).check(matches(isDisplayed()))
    }

    @Test
    fun testPartTwoButton() {
        onView(withId(R.id.btnPartTwo)).perform(click())
        onView(withText(R.string.part_2)).check(matches(isDisplayed()))
    }
}

