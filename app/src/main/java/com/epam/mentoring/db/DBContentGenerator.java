package com.epam.mentoring.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.dao.FlightDao;
import com.epam.mentoring.dao.ReviewDao;
import com.epam.mentoring.dao.TicketDao;
import com.epam.mentoring.dao.UserDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.Ticket;
import com.epam.mentoring.dao.model.User;

public class DBContentGenerator implements InitializingBean {

	@Autowired
	private FlightDao flightDao;

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private UserDao userDao;

	private static final List<String> FIRST_NAMES = Arrays.asList("Sophia", "Jackson", "Emma", "Aiden", "Olivia",
			"Lucas", "Ava", "Liam", "Mia", "Noah", "Isabella", "Ethan", "Riley", "Mason", "Aria", "Caden", "Zoe",
			"Oliver", "Charlotte", "Elijah", "Lily", "Grayson", "Layla", "Jacob", "Amelia", "Michael", "Emily",
			"Benjamin", "Madelyn", "Carter", "Aubrey", "James", "Adalyn", "Jayden", "Madison", "Logan", "Chloe",
			"Alexander", "Harper", "Caleb", "Abigail", "Ryan", "Aaliyah", "Luke", "Avery", "Daniel", "Evelyn", "Jack",
			"Kaylee", "William", "Ella", "Owen", "Ellie", "Gabriel", "Scarlett", "Matthew", "Arianna", "Connor",
			"Hailey", "Jayce", "Nora", "Isaac", "Addison", "Sebastian", "Brooklyn", "Henry", "Hannah", "Muhammad",
			"Mila", "Cameron", "Leah", "Wyatt", "Elizabeth", "Dylan", "Sarah", "Nathan", "Eliana", "Nicholas",
			"Mackenzie", "Julian", "Peyton", "Eli", "Maria", "Levi", "Grace", "Isaiah", "Adeline", "Landon", "Elena",
			"David", "Anna", "Christian", "Victoria", "Andrew", "Camilla", "Brayden", "Lillian", "John", "Natalie",
			"Lincoln");

	private static final List<String> LAST_NAMES = Arrays.asList("SMITH", "JOHNSON", "WILLIAMS", "JONES", "BROWN",
			"DAVIS", "MILLER", "WILSON", "MOORE", "TAYLOR", "ANDERSON", "THOMAS", "JACKSON", "WHITE", "HARRIS",
			"MARTIN", "THOMPSON", "GARCIA", "MARTINEZ", "ROBINSON", "CLARK", "RODRIGUEZ", "LEWIS", "LEE", "WALKER",
			"HALL", "ALLEN", "YOUNG", "HERNANDEZ", "KING", "WRIGHT", "LOPEZ", "HILL", "SCOTT", "GREEN", "ADAMS",
			"BAKER", "GONZALEZ", "NELSON", "CARTER", "MITCHELL", "PEREZ", "ROBERTS", "TURNER", "PHILLIPS", "CAMPBELL",
			"PARKER", "EVANS", "EDWARDS", "COLLINS", "STEWART", "SANCHEZ", "MORRIS", "ROGERS", "REED", "COOK", "MORGAN",
			"BELL", "MURPHY", "BAILEY", "RIVERA", "COOPER", "RICHARDSON", "COX", "HOWARD", "WARD", "TORRES", "PETERSON",
			"GRAY", "RAMIREZ", "JAMES", "WATSON", "BROOKS", "KELLY", "SANDERS", "PRICE");

	private static final Long PERIOD = 29800000l;
	private static final int PERIOD_PART = 5000;

	private static final List<String> HEADERS_FOR_REVIEW = Arrays.asList("Cool!", "Why not?", "Piece of shit!",
			"Bad...", "No. Definetly", "Do not flight with them", "Are you serious?", "Amazing!", "Take two!");

	private static final List<String> TEXT_FOR_REVIEW = Arrays.asList("SMITH", "JOHNSON", "WILLIAMS", "JONES", "BROWN",
			"DAVIS", "MILLER", "WILSON", "MOORE", "TAYLOR", "ANDERSON", "THOMAS", "JACKSON", "WHITE", "HARRIS",
			"MARTIN", "THOMPSON", "GARCIA", "MARTINEZ", "ROBINSON", "CLARK", "RODRIGUEZ", "LEWIS", "LEE", "WALKER",
			"HALL", "ALLEN", "YOUNG", "HERNANDEZ", "KING", "WRIGHT", "LOPEZ", "HILL", "SCOTT", "GREEN", "ADAMS",
			"BAKER", "GONZALEZ", "NELSON", "CARTER", "MITCHELL", "PEREZ", "ROBERTS", "TURNER", "PHILLIPS", "CAMPBELL",
			"PARKER", "EVANS", "EDWARDS", "COLLINS", "STEWART", "SANCHEZ", "MORRIS", "ROGERS", "REED", "COOK", "MORGAN",
			"BELL", "MURPHY", "BAILEY", "RIVERA", "COOPER", "RICHARDSON", "COX", "HOWARD", "WARD", "TORRES", "PETERSON",
			"GRAY", "RAMIREZ", "JAMES", "WATSON", "BROOKS", "KELLY", "SANDERS", "PRICE");

	private static final List<String> ENDPOINTS = Arrays.asList("SEOUL", "Sao Paulo", "Bombay", "JAKARTA", "Karachi",
			"MOSKVA (Moscow)", "Istanbul", "MEXICO", "Shanghai", "TOKYO", "", "BANGKOK", "BEIJING", "Delhi", "LONDON",
			"HongKong", "CAIRO", "TEHRAN", "BOGOTA", "Bandung", "Tianjin", "LIMA", "Rio de Janeiro", "Lahore", "Bogor",
			"SANTIAGO", "St Petersburg", "Shenyang", "Calcutta", "Wuhan", "Sydney", "Guangzhou", "SINGAPORE", "Madras",
			"BAGHDAD", "Pusan", "Los Angeles (CA)", "Yokohama", "DHAKA", "BERLIN", "Alexandria", "Bangalore", "Malang",
			"Hyderabad", "Chongqing", "Ho ChiMinh City", "Haerbin", "ANKARA", "BUENOS AIRES", "Chengdu", "Ahmedabad",
			"Casablanca", "Chicago (IL)", "Xian", "MADRID", "Surabaya", "PYONGYANG", "Nanjing", "KINSHASA", "ROMA",
			"Taipei", "Osaka", "KIEV", "YANGON", "Toronto", "Zibo", "Dalian", "Taegu", "ADDIS ABABA", "Jinan",
			"Salvador", "Inchon", "Semarang", "Giza", "Changchun", "Havanna", "Nagoya", "Belo Horizonte", "PARIS",
			"TASHKENT", "Fortaleza", "Sukabumi", "Cali", "Guayaquil", "Qingdao", "Izmir", "Cirebon", "Taiyuan",
			"BRASILIA", "BUCURESTI", "Faisalabad", "Quezon City", "Medan", "Houston (TX)", "Abidjan", "Mashhad",
			"Medellín", "Kanpur", "BUDAPEST", "CARACAS");

	@Override
	public void afterPropertiesSet() throws Exception {
		createUsers();
		createFlights();
		createTickets();
		createReviews();

	}

	private void createReviews() {
		final int headersSize = HEADERS_FOR_REVIEW.size();
		final int textSize = TEXT_FOR_REVIEW.size();
		List<Review> reviews = new ArrayList<>();

		for (Integer j = 1; j <= 300000; j++) {
			final Review review = new Review();
			review.setUserId(new Random().nextInt(10000 - 1) + 1);
			review.setHeader(HEADERS_FOR_REVIEW.get(new Random().nextInt(headersSize - 1)));
			review.setReview(TEXT_FOR_REVIEW.get(new Random().nextInt(textSize - 1)));
			review.setFlightId(new Random().nextInt(3000 - 1) + 1);
			review.setPostDate(new Timestamp(PERIOD * new Random().nextInt(PERIOD_PART)));

			reviews.add(review);

			if (j % 100 == 0) {
				reviewDao.createBatch(reviews);
				reviews = new ArrayList<>();
			}
		}

	}

	private void createTickets() {
		List<Ticket> tickets = new ArrayList<>();
		for (Integer j = 1; j <= 100000; j++) {
			final Ticket ticket = new Ticket();
			ticket.setFlightId(new Random().nextInt(3000 - 1) + 1);
			ticket.setUserId(new Random().nextInt(10000 - 1) + 1);
			ticket.setPlaceNumber(new Random().nextInt(1000) + 1);

			tickets.add(ticket);
			if (j % 100 == 0) {
				ticketDao.createBatch(tickets);
				tickets = new ArrayList<>();
			}
		}
	}

	private void createFlights() {
		final int endpointsSize = ENDPOINTS.size();
		List<Flight> flights = new ArrayList<>();

		for (Integer j = 1; j <= 3000; j++) {
			final Flight flight = new Flight();
			final int availableTicketCount = 10 + 10 * new Random().nextInt(20);
			flight.setAvailableTicketCount(availableTicketCount);
			flight.setDistance(10 + 100 * new Random().nextInt(20));
			flight.setCost(0.15 * flight.getDistance());
			flight.setEndingEndpointName(ENDPOINTS.get(new Random().nextInt(endpointsSize - 1)));
			flight.setStartingEndpointName(ENDPOINTS.get(new Random().nextInt(endpointsSize - 1)));
			flight.setFullTicketCount(availableTicketCount + new Random().nextInt(20));
			flight.setStartDate(new Timestamp(PERIOD * new Random().nextInt(PERIOD_PART)));
			flights.add(flight);
			if (j % 100 == 0) {
				flightDao.createBatch(flights);
				flights = new ArrayList<>();
			}
		}
	}

	private void createUsers() {
		final int firstSize = FIRST_NAMES.size();
		final int lastSize = LAST_NAMES.size();
		List<User> users = new ArrayList<>();

		for (Integer j = 1; j <= 10000; j++) {
			final User user = new User();
			user.setFirstName(FIRST_NAMES.get(new Random().nextInt(firstSize - 1)));
			user.setLastName(FIRST_NAMES.get(new Random().nextInt(lastSize - 1)));
			user.setLogin("USER" + j);
			user.setPassword("PASS" + j);
			users.add(user);

			if (j % 100 == 0) {
				userDao.createBatch(users);
				users = new ArrayList<>();
			}
		}

	}

}
