package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MovieTests {

    @Test
    void specialMovieWith20PercentDiscount() {
        LocalDate localDate = LocalDate.ofYearDay(2020,10);
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(localDate, LocalTime.of(17, 0, 0)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
        double ticketPrice = spiderMan.calculateTicketPrice(showing);

        System.out.println(Duration.ofMinutes(90));
        System.out.println(ticketPrice);
    }

    /**
     * purpose of this method is to test if the $1 discount is being applied for the 7th show sequence
     *
     */
    @Test
    void movieWithShowingOnSeventh(){
        LocalDate localDate = LocalDate.ofYearDay(2020,10);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Showing showing = new Showing(turningRed, 7, LocalDateTime.of(localDate, LocalTime.of(17, 0, 0)));
        double ticketPrice = turningRed.calculateTicketPrice(showing);
        assertEquals(10, ticketPrice);

        System.out.println(ticketPrice);
    }

    /**
     * purpose of this method is to test the false version of
     * the $1 discount  being applied for the 7th show sequence
     *
     */
    @Test
    void movieWithShowingNotOnSeventhDay(){
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Showing showing = new Showing(turningRed, 8, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        double ticketPrice = turningRed.calculateTicketPrice(showing);
        assertFalse(10 == ticketPrice);

        System.out.println(ticketPrice);
    }

    /**
     * purpose of this method is to test if the 25% discount is being
     * applied between 11 am - 4 pm
     *
     */
    @Test
    void movieWithShowingBetween11AMTo4PM(){
        LocalDate localDate = LocalDate.ofYearDay(2020,10);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 20, 0);
        Showing showing = new Showing(turningRed, 5, LocalDateTime.of(localDate, LocalTime.of(12, 0, 0)));
        double ticketPrice = turningRed.calculateTicketPrice(showing);
        assertEquals(15, ticketPrice);

        System.out.println(ticketPrice);
    }

    /**
     * purpose of this method is to test the false version of the 25% discount is being
     * applied between 11 am - 4 pm
     *
     */
    @Test
    void movieWithShowingNotBetween11AMTo4PM(){
        LocalDate localDate = LocalDate.ofYearDay(2020,10);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 20, 0);
        Showing showingAfter4PM = new Showing(turningRed, 5, LocalDateTime.of(localDate, LocalTime.of(17, 0, 0)));

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showingBefore11AM = new Showing(spiderMan, 5, LocalDateTime.of(localDate, LocalTime.of(10, 0, 0)));

        double ticketPriceTurningRed = turningRed.calculateTicketPrice(showingAfter4PM);
        double ticketSpiderMan = spiderMan.calculateTicketPrice(showingBefore11AM);

        assertFalse(15 == ticketPriceTurningRed);
        assertFalse(9.38 == ticketSpiderMan);


        System.out.println(ticketPriceTurningRed);
        System.out.println(ticketSpiderMan);
    }

    /**
     * purpose of this method is to test if the highest discount
     * will be applied for 3 scenarios (special movie, 7th movie, and 11am-4 pm scenarios)
     */
    @Test
    void movieWithMultipleScenarios(){
        LocalDate localDate = LocalDate.ofYearDay(2020,10);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 20, 1);
        Showing showing = new Showing(turningRed, 7, LocalDateTime.of(localDate, LocalTime.of(12, 0, 0)));

        double ticketPrice = turningRed.calculateTicketPrice(showing);
        assertEquals(15, ticketPrice);

        System.out.println(ticketPrice);
    }

    /**
     * purpose of this method is to test the false version of the highest discount
     * will be applied for 3 scenarios (special movie, 7th movie, and 11am-4 pm scenarios)
     */
    @Test
    void movieWithMultipleScenariosNotWorkingAsExpected(){
        LocalDate localDate = LocalDate.ofYearDay(2020,10);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 20, 1);
        Showing showing = new Showing(turningRed, 7, LocalDateTime.of(localDate, LocalTime.of(12, 0, 0)));

        double ticketPrice = turningRed.calculateTicketPrice(showing);
        assertFalse(16 == ticketPrice);

        System.out.println(ticketPrice);
    }

    /**
     * purpose of this method is to test if the highest discount
     * will be applied for 2 scenarios
     */
    @Test
    void movieWithTwoScenarios(){
        LocalDate localDate = LocalDate.ofYearDay(2020,10);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 20, 1);
        Showing showing = new Showing(turningRed, 2, LocalDateTime.of(localDate, LocalTime.of(18, 0, 0)));

        double ticketPrice = turningRed.calculateTicketPrice(showing);
        assertEquals(16, ticketPrice);

        System.out.println(ticketPrice);
    }

    /**
     * purpose of this method is to test the false version of the highest discount
     * will be applied for 2 scenarios
     */
    @Test
    void movieWithTwoScenariosNotWorkingAsExpected(){
        LocalDate localDate = LocalDate.ofYearDay(2020,10);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 40, 1);
        Showing showing = new Showing(turningRed, 5, LocalDateTime.of(localDate, LocalTime.of(12, 0, 0)));

        double ticketPrice = turningRed.calculateTicketPrice(showing);
        assertFalse(32 == ticketPrice);

        System.out.println(ticketPrice);
    }


}
