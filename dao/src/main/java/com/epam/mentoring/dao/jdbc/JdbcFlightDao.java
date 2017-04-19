package com.epam.mentoring.dao.jdbc;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.epam.mentoring.dao.FlightDao;
import com.epam.mentoring.dao.model.Flight;

@Repository
public class JdbcFlightDao extends AbstractCrudDao<Flight> implements FlightDao {

	@Autowired
	private JdbcTemplate template;

	private static final Integer PAGE_SIZE = 20;

	private static final String PAGINATION = " LIMIT ? OFFSET ? ORDER BY \"START_DATE\"";
	private static final String ENDPOINT_CONDITION = " WHERE (\"STARTING_ENDPOINT_NAME\"=?) OR (\"ENDING_ENDPOINT_NAME\"=?)";
	private static final String DATE_RANGE_CONDITION = " WHERE (\"START_DATE\">?) AND (\"START_DATE\"<?)";
	private static final String FLIGHT = "FLIGHT";

	@Override
	public List<Flight> getFlights(final Integer pageNumber) {
		return template.query(selectStringBuilder().append(PAGINATION).toString(),
				new Object[] { PAGE_SIZE, getOffsetMultiplier(pageNumber) * PAGE_SIZE },
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	@Override
	public List<Flight> getFlightsByEndpoint(final String endpointName, final Integer pageNumber) {
		return template.query(selectStringBuilder().append(ENDPOINT_CONDITION).append(PAGINATION).toString(),
				new Object[] { endpointName, endpointName, PAGE_SIZE, getOffsetMultiplier(pageNumber) * PAGE_SIZE },
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	@Override
	public List<Flight> getFlightsByDateRange(final Timestamp startFlightDate, final Timestamp finishFlightDate,
			final Integer pageNumber) {
		return template.query(
				selectStringBuilder().append(DATE_RANGE_CONDITION).append(PAGINATION).toString(), new Object[] {
						startFlightDate, finishFlightDate, PAGE_SIZE, getOffsetMultiplier(pageNumber) * PAGE_SIZE },
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	private int getOffsetMultiplier(final Integer pageNumber) {
		return pageNumber != null && pageNumber > 1 ? pageNumber - 1 : 0;
	}

	@Override
	protected Class<Flight> getModelClass() {
		return Flight.class;
	}

	@Override
	protected String getTableName() {
		return FLIGHT;
	}

}
