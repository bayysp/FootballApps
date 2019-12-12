package com.example.kadesubmisidua.view.activity

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.kadesubmisidua.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest{
    @Rule
    @JvmField
    var activityRule =  ActivityTestRule(SearchActivity::class.java)

    @Test
    fun testBehaviour(){
        //wait for 3 seconds
        Thread.sleep(3000)

        //clicked the search icon on actionbar
        Espresso.onView(ViewMatchers.withId(R.id.search_option_search)).perform(ViewActions.click())

        //wait for 3 seconds
        Thread.sleep(3000)

        Espresso.onView(ViewMatchers.isAssignableFrom(AutoCompleteTextView::class.java)).
            perform(
                ViewActions.typeText("napoli")
            ).perform(ViewActions.pressImeActionButton())

        Thread.sleep(3000)

        Espresso.onView(ViewMatchers.isAssignableFrom(AutoCompleteTextView::class.java)).
            perform(
                ViewActions.clearText()
            )

//        Espresso.onView(ViewMatchers.withId(R.id.search_option_search)).perform(ViewActions.clearText())

        Thread.sleep(3000)

        Espresso.onView(ViewMatchers.isAssignableFrom(AutoCompleteTextView::class.java)).
            perform(
                ViewActions.typeText("arsenal")
            ).perform(ViewActions.pressImeActionButton())

        Thread.sleep(5000)
    }
}