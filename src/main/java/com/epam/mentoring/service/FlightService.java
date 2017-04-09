package com.epam.mentoring.service;

import java.util.Date;
import java.util.List;

import com.epam.mentoring.dao.model.Flight;

public interface FlightService {

	List<Flight> getFlightsByDateRange(Date after, Date before, Integer pageNumber);

	List<Flight> getFlightsByEndpoint(String endpoint, Integer pageNumber);

	List<Flight> getFlights(Integer pageNumber);

	boolean createFlight(Flight flight);

	boolean deleteFlight(Flight flight);

	boolean updateFlight(Flight flight);

}
