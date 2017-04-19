package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Ticket;
import com.epam.mentoring.dao.model.User;

public interface TicketService extends SimpleCrudService<Ticket> {

	List<Ticket> getUserTickets(User user);

	List<Ticket> getTicketsForFlight(Flight flight);

}
