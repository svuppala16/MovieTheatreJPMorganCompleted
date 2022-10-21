package com.jpmc.theater;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        System.out.println("You have to pay " + reservation.totalFee());
        assertEquals(reservation.totalFee(), 37.5);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }

    @Test
    void printMovieScheduleJsonFormat() throws ParseException {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printScheduleJsonFormat();
    }

    /**
     * purpose of this method is to test the reserve method which prints the reservation details
     * in a json format
     *
     * @param  customer  an object passed in to get Customer details
     * @param  sequence  an int passed it to get the sequence for booking info
     * @param howManyTickets an int passed in to get ticket count
     * @return      Reservation(customer, showing, howManyTickets)
     */
    @Test
    void printReservationDetailsJsonFormat() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        theater.reserve(john, 6, 2);
    }
}
