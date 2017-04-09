package com.epam.mentoring.dao.jdbc;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.epam.mentoring.dao.FlightDao;
import com.epam.mentoring.dao.model.Flight;

@Repository
public class JdbcFlightDao implements FlightDao {

	@Autowired
	private JdbcTemplate template;

	private final static Integer PAGE_SIZE = 20;

	private final static String SELECT_NO_CONDITION = "SELECT * FROM \"FLIGHT\"";
	private final static String ID_CONDITION = " WHERE \"ID\"=?";
	private final static String PAGINATION = " LIMIT ? OFFSET ? ORDER BY \"START_DATE\"";
	private final static String ENDPOINT_CONDITION = " WHERE (\"STARTING_ENDPOINT_NAME\"=?) OR (\"ENDING_ENDPOINT_NAME\"=?)";
	private final static String DATE_RANGE_CONDITION = " WHERE (\"START_DATE\">?) AND (\"START_DATE\"<?)";
	private final static String INSERT = "INSERT INTO \"FLIGHT\" (\"STARTING_ENDPOINT_NAME\", \"ENDING_ENDPOINT_NAME\", \"DISTANCE\", \"COST\", \"START_DATE\", \"FULL_TICKET_COUNT\") VALUES (?,?,?,?,?,?)";
	private final static String UPDATE = "UPDATE \"FLIGHT\" SET \"STARTING_ENDPOINT_NAME\"=?, \"ENDING_ENDPOINT_NAME\"=?, \"DISTANCE\"=?, \"COST\"=?, \"START_DATE\"=?, \"FULL_TICKET_COUNT\"=? WHERE \"ID\"=?";
	private final static String DELETE = "DELETE FROM \"FLIGHT\" WHERE ID = ?";

	@Override
	public List<Flight> getFlights(final Integer pageNumber) {
		return template.query(SELECT_NO_CONDITION + PAGINATION,
				new Object[] { PAGE_SIZE, getOffsetMultiplier(pageNumber) * PAGE_SIZE },
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	@Override
	public List<Flight> getFlightsByEndpoint(final String endpointName, final Integer pageNumber) {
		return template.query(SELECT_NO_CONDITION + ENDPOINT_CONDITION + PAGINATION,
				new Object[] { endpointName, endpointName, PAGE_SIZE, getOffsetMultiplier(pageNumber) * PAGE_SIZE },
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	@Override
	public List<Flight> getFlightsByDateRange(final Timestamp startFlightDate, final Timestamp finishFlightDate,
			final Integer pageNumber) {
		return template.query(
				SELECT_NO_CONDITION + DATE_RANGE_CONDITION + PAGINATION, new Object[] { startFlightDate,
						finishFlightDate, PAGE_SIZE, getOffsetMultiplier(pageNumber) * PAGE_SIZE },
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	@Override
	public boolean saveFlight(final Flight flight) {
		return 1 == template
				.update(INSERT,
						new Object[] { flight.getStartingEndpointName(), flight.getEndingEndpointName(),
								flight.getDistance(), flight.getCost(), flight.getStartDate(),
								flight.getFullTicketCount() });
	}

	@Override
	public boolean deleteFlight(final Flight flight) {
		return 1 == template.update(DELETE, new Object[] { flight.getId() });
	}

	@Override
	public boolean updateFlight(final Flight flight) {
		return 1 == template
				.update(UPDATE,
						new Object[] { flight.getStartingEndpointName(), flight.getEndingEndpointName(),
								flight.getDistance(), flight.getCost(), flight.getStartDate(),
								flight.getFullTicketCount(), flight.getId() });
	}

	private int getOffsetMultiplier(final Integer pageNumber) {
		return pageNumber != null && pageNumber > 1 ? pageNumber - 1 : 0;
	}

	@Override
	public Flight getFlightById(final Integer fligtId) {
		return template.queryForObject(SELECT_NO_CONDITION + ID_CONDITION, new Object[] { fligtId },
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	@Override
	public Integer saveFlights(final List<Flight> flights) {
		Integer result = 0;
		for (final int[] i : Arrays.asList(template.batchUpdate(INSERT, flights.stream().map(flight -> {
			return new Object[] { flight.getStartingEndpointName(), flight.getEndingEndpointName(),
					flight.getDistance(), flight.getCost(), flight.getStartDate(), flight.getFullTicketCount() };
		}).collect(Collectors.toList())))) {
			result += IntStream.of(i).sum();
		}
		return result;
	}

}
