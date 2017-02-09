package com.bstirbat.timetracker.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TimeTrackReport {

    @Id
    @GeneratedValue
    private Long id;

    private String activityName;

    private Integer hours;

    private Integer minutes;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public TimeTrackReport() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimeTrackReport{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", date=" + date +
                '}';
    }
}
