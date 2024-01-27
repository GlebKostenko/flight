package org.kostenko.model;


import com.google.gson.annotations.SerializedName;

public class Flight {
    @SerializedName("no")
    private String numberOfDeparture;
    private Integer departure;
    private String from;
    private String to;
    private Integer duration;

    public Flight(String numberOfDeparture, Integer departure, String from, String to, Integer duration) {
        this.numberOfDeparture = numberOfDeparture;
        this.departure = departure;
        this.from = from;
        this.to = to;
        this.duration = duration;
    }

    public String getNumberOfDeparture() {
        return numberOfDeparture;
    }

    public void setNumberOfDeparture(String numberOfDeparture) {
        this.numberOfDeparture = numberOfDeparture;
    }

    public Integer getDeparture() {
        return departure;
    }

    public void setDeparture(Integer departure) {
        this.departure = departure;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}