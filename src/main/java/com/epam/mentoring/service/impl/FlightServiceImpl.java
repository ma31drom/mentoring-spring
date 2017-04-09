package com.epam.mentoring.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mentoring.dao.FlightDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightDao dao;

	@Override
	public List<Flight> getFlightsByDateRange(final Date after, final Date before, final Integer pageNumber) {
		return dao.getFlightsByDateRange(new Timestamp(after.getTime()), new Timestamp(before.getTime()), pageNumber);
	}

	@Override
	public List<Flight> getFlightsByEndpoint(final String endpoint, final Integer pageNumber) {
		return dao.getFlightsByEndpoint(endpoint, pageNumber);
	}

	@Override
	public List<Flight> getFlights(final Integer pageNumber) {
		return dao.getFlights(pageNumber);
	}

	@Override
	public boolean createFlight(final Flight flight) {
		flight.setAvailableTicketCount(flight.getFullTicketCount());
		return dao.saveFlight(flight);
	}

	@Override
	public boolean deleteFlight(final Flight flight) {
		return dao.deleteFlight(flight);
	}

	@Override
	public boolean updateFlight(final Flight flight) {
		return dao.updateFlight(flight);
	}

}
