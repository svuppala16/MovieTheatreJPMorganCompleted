package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    /**
     * @param customer customer details
     * @param showing showing details
     * @param audienceCount number of tickets
     */
    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    //changed the method to calculate the discount as well when returning the total fees
    /** returns total fee after discount is applied
     * @return showing.getMovie().calculateTicketPrice(showing) * audienceCount;
     */
    public double totalFee() {
        return showing.getMovie().calculateTicketPrice(showing) * audienceCount;
    }
}