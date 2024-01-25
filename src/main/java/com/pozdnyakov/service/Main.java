package com.pozdnyakov.service;

import java.util.*;




public class Main {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, List<MeterReading>> readings = new HashMap<>();

    public static void main(String[] args) {
        // Регистрация пользователя
        registerUser("user1", "password1");

        // Авторизация пользователя
        User user = login("user1", "password1");

        // Подача показаний
        submitMeterReading(user, "hotWater", 100);

        // Получение актуальных показаний
        List<MeterReading> latestReadings = getLatestMeterReadings(user);


        // Просмотр показаний за конкретный месяц
        List<MeterReading> readingsForMonth = getMeterReadingsForMonth(user, 01, 2024);
        for(MeterReading a:readingsForMonth){
            System.out.println(a.getValue());
        }
        // Просмотр истории подачи показаний
        List<MeterReading> readingHistory = getMeterReadingHistory(user);
    }

    //Метод для регистрации пользователей
    public static void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    //Метод для авторизации пользователей
    public static User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    //Метод отправки показаний счетчика
    public static void submitMeterReading(User user, String type, int value) {
        List<MeterReading> userReadings = readings.getOrDefault(user.getUsername(), new ArrayList<>());
        userReadings.add(new MeterReading(type, value, new Date()));
        readings.put(user.getUsername(), userReadings);
    }

    //Метод получения актуальных показаний счетчика
    public static List<MeterReading> getLatestMeterReadings(User user) {
        return readings.getOrDefault(user.getUsername(), new ArrayList<>());
    }

    //Метод просмотра показаний за последний месяц
    public static List<MeterReading> getMeterReadingsForMonth(User user, int month, int year) {
        List<MeterReading> userReadings = readings.getOrDefault(user.getUsername(), new ArrayList<>());
        List<MeterReading> readingsForMonth = new ArrayList<>();
        for (MeterReading reading : userReadings) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(reading.getDate());
            if (cal.get(Calendar.MONTH) + 1 == month && cal.get(Calendar.YEAR) == year) {
                readingsForMonth.add(reading);
            }
        }
        return readingsForMonth;
    }

    //Метод истории подачи показаний
    public static List<MeterReading> getMeterReadingHistory(User user) {
        return readings.getOrDefault(user.getUsername(), new ArrayList<>());
    }
}
