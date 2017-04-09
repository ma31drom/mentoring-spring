package com.epam.mentoring.dao;

import java.sql.Timestamp;
import java.util.List;

import com.epam.mentoring.dao.model.Flight;

public interface FlightDao {

	List<Flight> getFlights(Integer pageNumber);

	Flight getFlightById(Integer fligtId);

	List<Flight> getFlightsByEndpoint(String endpointName, Integer pageNumber);

	boolean saveFlight(Flight flight);

	Integer saveFlights(List<Flight> flight);

	boolean updateFlight(Flight flight);

	boolean deleteFlight(Flight flight);

	List<Flight> getFlightsByDateRange(Timestamp startFlightDate, Timestamp finishFlightDate, Integer pageNumber);

}
