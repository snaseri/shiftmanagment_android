package com.example.myapplication.myapplication.recordkeeper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PhoneNoHasNumbersNotLettersTest {
    @Test
    public void validateType() {

        assertFalse(NewAgencyFragment.invalidValidateType("6561674639f"));
        assertTrue(NewAgencyFragment.invalidValidateType("6561674639"));
    }
}