package com.android.trovi.Models;

public class MeetingModel {
    private String name;
    private String time;

    public MeetingModel() {
    }

    public MeetingModel(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
