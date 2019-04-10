package com.example.myapplication.myapplication.recordkeeper;


import android.arch.persistence.room.Room;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.myapplication.recordkeeper.database.Agency;
import com.example.myapplication.myapplication.recordkeeper.database.Company;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InstrumentedTestAddCompanyAgencyPage {

    public static Matcher withIDCompany(final Integer id){
        return new TypeSafeMatcher<Company>(){
            @Override
            public boolean matchesSafely(Company c) {
                return c.getId() == id;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static Matcher withIDAgency(final Integer id){
        return new TypeSafeMatcher<Agency>(){
            @Override
            public boolean matchesSafely(Agency a) {
                return a.getId() == id;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static Matcher withName(final String name){
        return new TypeSafeMatcher<Company>(){
            @Override
            public boolean matchesSafely(Company person) {
                System.out.println(person.getName());
                return person.getName() == name;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void clearDB(){
        ShiftlogDAO db = Room.databaseBuilder(mActivityTestRule.getActivity(), ShiftlogDatabase .class,
                "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();
        db.clearCompanies();
        db.clearAgencies();
        db.clearShiftlogs();
    }


    @Test
    public void addCompanyTest() {
        onView(allOf(withId(R.id.companyInput))).perform(scrollTo(), click());

        onData(withIDCompany(-2)).perform(click());
        //withName(new Company("Add Company", "0")));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.CompanyInput),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.CompanyInput),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatEditText2.perform(scrollTo(), replaceText("The Company"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.phoneNumberInput),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                4)));
        appCompatEditText3.perform(scrollTo(), replaceText("01234567890"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.Company_Save_Button), withText("Save"),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                5)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.companyInput),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                9)));
        appCompatSpinner2.perform(scrollTo(), click());

        onData(instanceOf(Company.class)).atPosition(1).perform(click());


        onView(withId(R.id.companyInput)).check(matches(withSpinnerText("The Company")));
    }

    @Test
    public void AddAgencyTest() {
        onView(allOf(withId(R.id.AgencyInput))).perform(scrollTo(), click());

        onData(withIDAgency(-2)).perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.AgencyInput),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.AgencyInput),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatEditText2.perform(scrollTo(), replaceText("The Agency"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.phoneNumberInput),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                4)));
        appCompatEditText3.perform(scrollTo(), replaceText("01234567890"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.Agency_Save_Button), withText("Save"),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                5)));
        appCompatButton.perform(scrollTo(), click());


        onView(allOf(withId(R.id.AgencyInput))).perform(scrollTo(), click());

        onData(instanceOf(Agency.class)).atPosition(1).perform(click());


        onView(withId(R.id.AgencyInput)).check(ViewAssertions.matches(withSpinnerText("The Agency")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
