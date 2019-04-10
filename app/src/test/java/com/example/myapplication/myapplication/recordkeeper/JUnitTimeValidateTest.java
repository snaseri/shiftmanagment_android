package com.example.myapplication.myapplication.recordkeeper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTimeValidateTest {



    @Test
    public void validateDate() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:00", "01/01/2019",
                                        "12:00", "01/01/2020",
                                        2019, 1, 1,
                                        12, 0,
                                        2020, 1, 1,
                                        12, 0);
        assertEquals(validTime, true);
    }


    @Test(expected = AssertionError.class)
    public void invalidDate() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:00", "01/01/2020",
                                        "12:00", "01/01/2019",
                                        2020, 1, 1,
                                        12, 0,
                                        2019, 1, 1,
                                        12, 0);
        assertEquals(validTime, true);
    }



    @Test
    public void validateDateMonth() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:00", "01/01/2019",
                                        "12:00", "01/02/2019",
                                        2019, 1, 1,
                                        12, 0,
                                        2019, 2, 1,
                                        12, 0);
        assertEquals(validTime, true);
    }


    @Test(expected = AssertionError.class)
    public void invalidDateMonth() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:00", "01/02/2019",
                                        "12:00", "01/01/2019",
                                        2019, 2, 1,
                                        12, 0,
                                        2019, 1, 1,
                                        12, 0);
        System.out.print(validTime);
        assertEquals(validTime, true);
    }



    @Test
    public void validateDateDay() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:00", "01/01/2019",
                                        "12:00", "02/01/2019",
                                        2019, 1, 1,
                                        12, 0,
                                        2019, 1, 2,
                                        12, 0);
        assertEquals(validTime, true);
    }


    @Test(expected = AssertionError.class)
    public void invalidDateDay() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:00", "02/01/2019",
                                        "12:00", "01/01/2019",
                                        2019, 1, 2,
                                        12, 0,
                                        2019, 1, 1,
                                        12, 0);
        assertEquals(validTime, true);
    }



    @Test
    public void validateTimeHour() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:00", "01/01/2019",
                                        "20:00", "01/01/2019",
                                        2019, 1, 1,
                                        12, 0,
                                        2019, 1, 1,
                                        20, 0);
        assertEquals(validTime, true);
    }


    @Test(expected = AssertionError.class)
    public void invalidTimeHour() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("20:00", "01/01/2019",
                                        "20:00", "01/01/2019",
                                        2019, 1, 1,
                                        12, 0,
                                        2019, 1, 1,
                                        20, 0);
        assertEquals(validTime, true);
    }



    @Test
    public void validateTimeMinute() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:00", "01/01/2019",
                                        "12:30", "01/01/2019",
                                        2019, 1, 1,
                                        12, 0,
                                        2019, 1, 1,
                                        12, 30);
        assertEquals(validTime, true);
    }


    @Test(expected = AssertionError.class)
    public void invalidTimeMinute() {
        MainActivity mainActivity = new MainActivity();

        Boolean validTime =
                mainActivity.validateTimes("12:30", "01/01/2019",
                                        "12:00", "01/01/2019",
                                        2019, 1, 1,
                                        12, 30,
                                        2019, 1, 1,
                                        12, 0);
        assertEquals(validTime, true);
    }
}