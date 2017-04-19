package com.epam.mentoring.dao;

import java.sql.Timestamp;
import java.util.List;

import com.epam.mentoring.dao.model.Flight;

public interface FlightDao extends CrudOpertaions<Flight> {

	List<Flight> getFlights(Integer pageNumber);

	List<Flight> getFlightsByEndpoint(String endpointName, Integer pageNumber);

	List<Flight> getFlightsByDateRange(Timestamp startFlightDate, Timestamp finishFlightDate, Integer pageNumber);

}
