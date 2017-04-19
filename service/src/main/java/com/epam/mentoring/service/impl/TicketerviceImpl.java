package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mentoring.dao.CrudOpertaions;
import com.epam.mentoring.dao.FlightDao;
import com.epam.mentoring.dao.TicketDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Ticket;
import com.epam.mentoring.dao.model.User;
import com.epam.mentoring.service.TicketService;

@Service
public class TicketerviceImpl extends AbstractCrudService<Ticket> implements TicketService {

	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private FlightDao flightDao;

	@Transactional
	@Override
	public boolean create(final Ticket ticket) {
		final Flight flightById = flightDao.get(ticket.getFlightId());
		final Integer fullTicketCount = flightById.getFullTicketCount();
		final Integer availableTicketCount = flightById.getAvailableTicketCount() - 1;
		flightById.setAvailableTicketCount(availableTicketCount);
		flightDao.update(flightById);
		ticket.setPlaceNumber(fullTicketCount - availableTicketCount);
		return ticketDao.create(ticket);
	}

	@Override
	public List<Ticket> getUserTickets(final User user) {
		return ticketDao.getUserTickets(user);
	}

	@Override
	public List<Ticket> getTicketsForFlight(final Flight flight) {
		return ticketDao.getTicketsForFlight(flight);
	}

	@Override
	protected CrudOpertaions<Ticket> getDao() {
		return ticketDao;
	}

}
