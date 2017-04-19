package com.epam.mentoring.service;

import java.util.Date;
import java.util.List;

import com.epam.mentoring.dao.model.Flight;

public interface FlightService extends SimpleCrudService<Flight> {

	List<Flight> getFlightsByDateRange(Date after, Date before, Integer pageNumber);

	List<Flight> getFlightsByEndpoint(String endpoint, Integer pageNumber);

	List<Flight> getFlights(Integer pageNumber);

}
