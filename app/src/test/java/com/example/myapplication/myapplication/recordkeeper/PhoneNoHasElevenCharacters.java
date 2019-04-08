package com.example.myapplication.myapplication.recordkeeper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PhoneNoHasElevenCharacters {
    @Test
    public void validateLength() {
        assertTrue(NewAgencyFragment.validateLength(11));

    }
}
