package com.jpmc.theater;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Movie {
	private static int MOVIE_CODE_SPECIAL = 1;

	private String title;
	private String description;
	private Duration runningTime;
	private double ticketPrice;
	private int specialCode;


	public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
		this.title = title;
		this.runningTime = runningTime;
		this.ticketPrice = ticketPrice;
		this.specialCode = specialCode;
	}

	public String getTitle() {
		return title;
	}

	public Duration getRunningTime() {
		return runningTime;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public double calculateTicketPrice(Showing showing) {
		return ticketPrice - getDiscount(showing);
	}

	/**
	 * a new method that was created to apply the discount to special movies
	 *
	 * @param  showing  an object passed in to get showing details
	 * @return      special discount
	 */
	private double calculateSpecialDiscount(Showing showing) {
		double specialDiscount = 0;
		if (MOVIE_CODE_SPECIAL == specialCode) {
			specialDiscount = ticketPrice * 0.2; // 20% discount for special movie
		}
		return specialDiscount;
	}

	/**
	 * a new method created to calculate the discount based on the movie sequence
	 *
	 * @param  showing  an object passed in to get showing details
	 * @return      sequence discount
	 */
	private double calculateSequenceDiscount(Showing showing) {
		double sequenceDiscount = 0;
		int showSequence = showing.getSequenceOfTheDay();
		if(showSequence == 1)
		{
			sequenceDiscount = 3; // $3 discount for 1st show
		}
		else if(showSequence == 2) {
			sequenceDiscount = 2; // $2 discount for 2nd show
		}
		else if(showSequence == 7) {
			sequenceDiscount = 1; // $1 discount for 7th show as mentioned in instructions
		}
		return sequenceDiscount;
	}

	/**
	 * a new method created to calculate the discount based on the showtimes
	 * in this scenario, we are calculating the 25% discount for when the movie time at 11 am to 4 pm
	 *
	 * @param  showing  an object passed in to get showing details
	 * @return      showTimeDiscount
	 */
	private double calculateShowtimeDiscount(Showing showing) {
		// getHour return 0 - 23 hours
		int hour = showing.getStartTime().getHour();
		double showTimeDiscount = 0;

		if (hour >= 11 && hour <= 16) {
			showTimeDiscount = ticketPrice * 0.25;
		}
		return showTimeDiscount;
	}

	/**
	 * applies all the different discounts and take only the biggest discount
	 *
	 * @param  showing  an object passed in to get showing details
	 * @return      Collections.max(discountList)
	 */
	private double getDiscount(Showing showing) {
		List<Double> discountList = new ArrayList<Double>();
		discountList.add(calculateSpecialDiscount(showing));
		discountList.add(calculateSequenceDiscount(showing));
		discountList.add(calculateShowtimeDiscount(showing));

		return Collections.max(discountList);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Movie movie = (Movie) o;
		return Double.compare(movie.ticketPrice, ticketPrice) == 0 && Objects.equals(title, movie.title)
				&& Objects.equals(description, movie.description) && Objects.equals(runningTime, movie.runningTime)
				&& Objects.equals(specialCode, movie.specialCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
	}
}