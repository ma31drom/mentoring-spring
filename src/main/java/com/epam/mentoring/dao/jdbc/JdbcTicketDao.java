package com.epam.mentoring.dao.jdbc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.epam.mentoring.dao.TicketDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Ticket;
import com.epam.mentoring.dao.model.User;

@Repository
public class JdbcTicketDao implements TicketDao {

	@Autowired
	private JdbcTemplate template;

	private static final String SELECT_NO_CONDITION = "SELECT * FROM \"TICKET\"";
	private static final String USER_CONDITION = " WHERE (\"USER_ID\"=?)";
	private static final String FLIGHT_CONDITION = " WHERE (\"FLIGHT_ID\"=?)";
	private static final String INSERT = "INSERT INTO \"TICKET\" (\"PLACE_NUMBER\", \"FLIGHT_ID\", \"USER_ID\") VALUES (?,?,?)";
	private static final String DELETE = "DELETE FROM \"TICKET\" WHERE ID = ?";

	@Override
	public List<Ticket> getUserTickets(final User user) {
		return template.query(SELECT_NO_CONDITION + USER_CONDITION, new Object[] { user.getId() },
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
	}

	@Override
	public boolean saveTicket(final Ticket ticket) {
		return 1 == template.update(INSERT,
				new Object[] { ticket.getPlaceNumber(), ticket.getFlightId(), ticket.getUserId() });
	}

	@Override
	public List<Ticket> getTicketsForFlight(final Flight flight) {
		return template.query(SELECT_NO_CONDITION + FLIGHT_CONDITION, new Object[] { flight.getId() },
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
	}

	@Override
	public Integer saveTickets(final List<Ticket> tickets) {

		Integer result = 0;
		List<Object[]> collect = tickets.stream().map(ticket -> {
			return new Object[] { ticket.getPlaceNumber(), ticket.getFlightId(), ticket.getUserId() };
		}).collect(Collectors.toList());
		for (final int[] i : Arrays.asList(template.batchUpdate(INSERT, collect))) {
			result += IntStream.of(i).sum();
		}
		return result;
	}

	@Override
	public boolean deleteTicket(final Ticket ticket) {
		return 1 == template.update(DELETE, new Object[] { ticket.getId() });

	}

}
