package com.jpmc.theater;

import java.time.Duration;
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

	private double getDiscount(Showing showing) {
		double specialDiscount = 0;
		if (MOVIE_CODE_SPECIAL == specialCode) {
			specialDiscount = ticketPrice * 0.2; // 20% discount for special movie
		}

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
//		default:
//			throw new IllegalArgumentException("failed exception");
		//}

		// getHour return 0 - 23 hours
		int hour = showing.getStartTime().getHour();
		double showTimeDiscount = 0;

		if (hour >= 11 && hour <= 16) {
			showTimeDiscount = showTimeDiscount * 0.25;
		}

		double discount = (specialDiscount > sequenceDiscount) // ? specialDiscount : sequenceDiscount
				? ((specialDiscount > showTimeDiscount) ? specialDiscount : showTimeDiscount) // specialDiscount
				: ((sequenceDiscount > showTimeDiscount) ? sequenceDiscount : showTimeDiscount); // sequenceDiscount
		// biggest discount will win
		return discount;
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