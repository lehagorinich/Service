package com.pozdnyakov.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testRegisterUser() {
        Main.registerUser("user1", "password1");
        assertNotNull(Main.login("user1", "password1"));
    }

    @Test
    void testLogin() {
        Main.registerUser("user2", "password2");
        assertNotNull(Main.login("user2", "password2"));
        assertNull(Main.login("user2", "wrongPassword"));
        assertNull(Main.login("nonExistingUser", "password"));
    }

    @Test
    void testSubmitMeterReading() {
        User user = new User("user3", "password3");
        Main.submitMeterReading(user, "electricity", 200);
        List<MeterReading> readings = Main.getLatestMeterReadings(user);
        assertEquals(1, readings.size());
        assertEquals("electricity", readings.get(0).getType());
        assertEquals(200, readings.get(0).getValue());
    }

    @Test
    void testGetMeterReadingsForMonth() {
        User user = new User("user4", "password4");
        Main.submitMeterReading(user, "water", 300);
        Main.submitMeterReading(user, "water", 400);
        List<MeterReading> readingsForMonth = Main.getMeterReadingsForMonth(user, 01, 2024);
        assertEquals(2, readingsForMonth.size());
    }

    @Test
    void testGetMeterReadingHistory() {
        User user = new User("user5", "password5");
        Main.submitMeterReading(user, "gas", 500);
        Main.submitMeterReading(user, "gas", 600);
        List<MeterReading> readingHistory = Main.getMeterReadingHistory(user);
        assertEquals(2, readingHistory.size());
    }
}