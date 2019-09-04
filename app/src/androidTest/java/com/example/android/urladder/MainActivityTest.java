package com.example.android.urladder;




import android.support.test.espresso.Espresso;

import android.support.test.runner.AndroidJUnit4;


import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.action.ViewActions.typeText;

import static android.support.test.espresso.matcher.ViewMatchers.withId;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {



    @Rule public final ActivityRule<MainActivity> main = new ActivityRule<>(MainActivity.class);



    @Test
    public void shouldBeAbleToPerformPostRequest(){
        onView(withId(R.id.add_bookmark)).perform(click());
        onView(withId(R.id.add_folder_name)).perform(typeText("Folder_Name"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.add_url_name)).perform(typeText("www.website.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.add)).perform(click());
        onView(withText(R.string.Toast_String)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));             //Checks the Toast string if the http
                                                                                                              // response code for post is correct or not.



    }

    @Test
    public void shouldBeAbleToPerformGetRequest(){
      onView(withId(R.id.button)).perform(click());
        onView(withText(R.string.Toast_String1)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));         //Checks the Toast string if the http
                                                                                                              // response code for get is correct or not.

    }



}


