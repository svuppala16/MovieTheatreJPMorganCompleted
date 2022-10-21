package com.jpmc.theater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.ParseException;


public class Theater {

	LocalDateProvider provider;
	private List<Showing> schedule;

	public Theater(LocalDateProvider provider) {
		this.provider = provider;

		Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
		Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
		Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
		schedule = List.of(new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
				new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
				new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
				new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
				new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
				new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
				new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
				new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
				new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));
	}


	/**
	 * purpose of this method is to add all the reservation details into an object and convert into a json format
	 * 	which will be printed out in the createReservation() method
	 *
	 * @param  customer  an object passed in to get Customer details
	 * @param  sequence  an int passed it to get the sequence for booking info
	 * @param howManyTickets an int passed in to get ticket count
	 * @return      Reservation(customer, showing, howManyTickets)
	 */
	public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
		Showing showing;
		try {
			showing = schedule.get(sequence - 1);
			double totalTicketPrice = showing.calculateFee(howManyTickets);
			ReservationDetails reservationDetails=new ReservationDetails();
			reservationDetails.setCustomerName(customer.getName());
			reservationDetails.setNumberOfTickets(howManyTickets);
			reservationDetails.setShowStartTime(showing.getStartTime().toString());
			reservationDetails.setTitle(showing.getMovie().getTitle());
			reservationDetails.setTotalAmount(totalTicketPrice);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			System.out.println("Below are the confirmation details:");
			System.out.println(gson.toJson(reservationDetails));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
		}
		return new Reservation(customer, showing, howManyTickets);
	}

	/**
	 * in the README.md, the instructions were that the schedule must print in simple text format
	 * and in a json format. The schedule list is converted to json
	 *
	 */
	public void printScheduleJsonFormat() throws ParseException {
		List<Showing> newShowingsList = new ArrayList(schedule);
		Collections.sort(newShowingsList, new SortbySequence());

		List<ScheduleDetails> scheduleList = new ArrayList<ScheduleDetails>();



		System.out.println("================================== Start JSON Output for Schedule ===============================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		for (Showing s : newShowingsList) {
			ScheduleDetails scheduleDetails = new ScheduleDetails();
			scheduleDetails.setSequenceOfTheDay(s.getSequenceOfTheDay());
			scheduleDetails.setShowStartTime(s.getStartTime().toString());
			scheduleDetails.setTitle(s.getMovie().getTitle());
			scheduleDetails.setRunningTime(humanReadableFormat(s.getMovie().getRunningTime()));
			scheduleDetails.setTicketPrice(s.getMovieFee());
			scheduleList.add(scheduleDetails);
		}
		System.out.println(gson.toJson(scheduleList));
		System.out.println("=================================================================================================");
	}

	//original method to print schedule
	public void printSchedule() {
		System.out.println(provider.currentDate());
		System.out.println("===================================================");
		schedule.forEach(s -> System.out
				.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " "
						+ humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee()));
		System.out.println("===================================================");
	}


	public String humanReadableFormat(Duration duration) {
		long hour = duration.toHours();
		long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

		return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin,
				handlePlural(remainingMin));
	}

	private String handlePlural(long value) {
		if (value == 1) {
			return "";
		} else {
			return "s";
		}
	}


	/**
	 * createReservation() prints the reservation confirmation details and takes the input from the customer
	 * 	about the movie booking details. This method calls reserve(customer, seqNumber, numberOftickets) to print
	 * 	the details in a json format
	 */
	public void createReservation(){

		try {
			BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the sequence number to make the reservation: ");
			int seqNumber = Integer.parseInt(reader.readLine());
			System.out.println("Enter your Name: ");
			String customerName = reader.readLine();
			System.out.println("Enter Number of tickets: ");
			int numberOftickets = Integer.parseInt(reader.readLine());

			Customer customer = new Customer(customerName, "123");

			reserve(customer, seqNumber, numberOftickets);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) throws ParseException {
		Theater theater = new Theater(LocalDateProvider.singleton());
		// Printing the schedule.
		theater.printSchedule();
		//print schedule in json format
		theater.printScheduleJsonFormat();
		// Printing reservations in JSON format
		theater.createReservation();



	}

}
