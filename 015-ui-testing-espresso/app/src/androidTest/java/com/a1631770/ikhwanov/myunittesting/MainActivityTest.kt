package com.a1631770.ikhwanov.myunittesting

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
  private val dummyVolume = "504.0"
  private val dummycircumference = "100.0"
  private val dummySurfaceArea = "396.0"
  private val dummyLength = "12.0"
  private val dummyWidth = "7.0"
  private val dummyHeigth = "6.0"
  private val emptyInput = ""
  private val fieldEmpty = "Field ini tidak boleh kosong"

  @Before
  fun setup() {
    ActivityScenario.launch(MainActivity::class.java)
  }

  @Test
  fun assertGetCircumference() {
    onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
    onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
    onView(withId(R.id.edt_heigth)).perform(typeText(dummyHeigth), closeSoftKeyboard())

    onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
    onView(withId(R.id.btn_save)).perform(click())

    onView(withId(R.id.btn_circumfarence)).check(matches(isDisplayed()))
    onView(withId(R.id.btn_circumfarence)).perform(click())

    onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
    onView(withId(R.id.tv_result)).check(matches(withText(dummycircumference)))
  }

  @Test
  fun assertGetSurfaceArea() {
    onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
    onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
    onView(withId(R.id.edt_heigth)).perform(typeText(dummyHeigth), closeSoftKeyboard())

    onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
    onView(withId(R.id.btn_save)).perform(click())

    onView(withId(R.id.btn_surface_area)).check(matches(isDisplayed()))
    onView(withId(R.id.btn_surface_area)).perform(click())

    onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
    onView(withId(R.id.tv_result)).check(matches(withText(dummySurfaceArea)))
  }

  @Test
  fun assertGetVolume() {
    onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
    onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
    onView(withId(R.id.edt_heigth)).perform(typeText(dummyHeigth), closeSoftKeyboard())

    onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
    onView(withId(R.id.btn_save)).perform(click())

    onView(withId(R.id.btn_volume)).check(matches(isDisplayed()))
    onView(withId(R.id.btn_volume)).perform(click())

    onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
    onView(withId(R.id.tv_result)).check(matches(withText(dummyVolume)))
  }

  //Pengecekan untuk empty input
//  @Test
//  fun assertEmptyInput() {
//    //pengecekan input pada length
//    onView(withId(R.id.edt_length)).perform(typeText(emptyInput), closeSoftKeyboard())
//
//    onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
//    onView(withId(R.id.btn_save)).perform(click())
//
//    onView(withId(R.id.edt_length)).check(matches(hasErrorText(fieldEmpty)))
//    onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
//
//    //pengecekan input pada width
//    onView(withId(R.id.edt_width)).perform(typeText(emptyInput), closeSoftKeyboard())
//
//    onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
//    onView(withId(R.id.btn_save)).perform(click())
//
//    onView(withId(R.id.edt_width)).check(matches(hasErrorText(fieldEmpty)))
//    onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
//
//    //pengecekan input pada heigth
//    onView(withId(R.id.edt_heigth)).perform(typeText(emptyInput), closeSoftKeyboard())
//
//    onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
//    onView(withId(R.id.btn_save)).perform(click())
//
//    onView(withId(R.id.edt_heigth)).check(matches(hasErrorText(fieldEmpty)))
//    onView(withId(R.id.edt_heigth)).perform(typeText(dummyHeigth), closeSoftKeyboard())
//
//    onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
//    onView(withId(R.id.btn_save)).perform(click())
//  }

}