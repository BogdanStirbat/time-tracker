package com.bstirbat.timetracker.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ActivityDurationReport {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer hours;

    private Integer minutes;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public ActivityDurationReport() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "ActivityDurationReport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", date=" + date +
                '}';
    }
}
