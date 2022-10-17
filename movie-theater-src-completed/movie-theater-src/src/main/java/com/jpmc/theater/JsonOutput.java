package com.jpmc.theater;

import java.util.Objects;

public class JsonOutput {
	private int sequenceOfTheDay;
	private String startTime;
	private String title;
	private String runningTime;
	private String movieFees;

	public JsonOutput(int sequenceOfTheDay, String startTime, String title, String runningTime, String movieFees) {
		super();
		this.sequenceOfTheDay = sequenceOfTheDay;
		this.startTime = startTime;
		this.title = title;
		this.runningTime = runningTime;
		this.movieFees = movieFees;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieFees, runningTime, sequenceOfTheDay, startTime, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonOutput other = (JsonOutput) obj;
		return Objects.equals(movieFees, other.movieFees) && Objects.equals(runningTime, other.runningTime)
				&& sequenceOfTheDay == other.sequenceOfTheDay && Objects.equals(startTime, other.startTime)
				&& Objects.equals(title, other.title);
	}

}
