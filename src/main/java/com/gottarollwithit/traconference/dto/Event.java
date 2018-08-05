package com.gottarollwithit.traconference.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
public class Event {

    private String name;
    private Integer duration;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer trackNo;

    public Event(String name, Integer duration, LocalTime startTime, Integer trackNo) {
        this.name = name;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(duration);
        this.trackNo = trackNo;
    }

    public Event(String name, LocalTime startTime, LocalTime endTime, Integer trackNo) {
        this.name = name;
        this.duration = (int) Duration.between(startTime, endTime).toMinutes();
        this.startTime = startTime;
        this.endTime = endTime;
        this.trackNo = trackNo;
    }
}