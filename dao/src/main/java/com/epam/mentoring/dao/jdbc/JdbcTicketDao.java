package com.epam.mentoring.dao.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.epam.mentoring.dao.TicketDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Ticket;
import com.epam.mentoring.dao.model.User;

@Repository
public class JdbcTicketDao extends AbstractCrudDao<Ticket> implements TicketDao {

	@Autowired
	private JdbcTemplate template;

	private static final String USER_CONDITION = " WHERE (\"USER_ID\"=?)";
	private static final String FLIGHT_CONDITION = " WHERE (\"FLIGHT_ID\"=?)";
	private static final String TICKET = "TICKET";

	@Override
	public List<Ticket> getUserTickets(final User user) {
		return template.query(selectStringBuilder().append(USER_CONDITION).toString(), new Object[] { user.getId() },
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
	}

	@Override
	public List<Ticket> getTicketsForFlight(final Flight flight) {
		return template.query(selectStringBuilder().append(FLIGHT_CONDITION).toString(),
				new Object[] { flight.getId() }, new BeanPropertyRowMapper<Ticket>(Ticket.class));
	}

	@Override
	protected Class<Ticket> getModelClass() {
		return Ticket.class;
	}

	@Override
	protected String getTableName() {
		return TICKET;
	}

}
