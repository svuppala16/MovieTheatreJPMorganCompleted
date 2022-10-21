package com.jpmc.theater;

import java.time.LocalDateTime;
import java.util.Comparator;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    /**
     * @param movie movie details
     * @param sequenceOfTheDay sequence of movie
     * @param showStartTime start time of movie
     */
    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double calculateFee(int audienceCount) {
        return movie.calculateTicketPrice(this) * audienceCount;
    }


}


/**
 * created this class to be able to sort through the array list for schedule in a scenario where it
 * is required
 */

class SortbySequence implements Comparator<Showing> {

    // Method
    // Sorting in ascending order of roll number
    public int compare(Showing a, Showing b)
    {

        return a.getSequenceOfTheDay() - b.getSequenceOfTheDay();
    }
}
