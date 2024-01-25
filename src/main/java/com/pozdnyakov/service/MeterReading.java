package com.pozdnyakov.service;

import java.util.Date;

public class MeterReading {
    private String type;
    private int value;
    private Date date;

    public MeterReading(String type, int value, Date date) {
        this.type = type;
        this.value = value;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // геттеры и сеттеры
}

