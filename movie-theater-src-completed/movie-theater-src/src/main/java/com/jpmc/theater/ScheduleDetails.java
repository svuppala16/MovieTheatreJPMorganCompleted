package com.jpmc.theater;

/**
 * created this class to consolidate all the reservation details that a customer books
 * this class is used in the theatre class to print the details in a json format
 */


public class ScheduleDetails {

    int sequenceOfTheDay;
    String showStartTime;
    String title;
    String runningTime;
    double ticketPrice;

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public void setSequenceOfTheDay(int sequenceOfTheDay) {
        this.sequenceOfTheDay = sequenceOfTheDay;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
