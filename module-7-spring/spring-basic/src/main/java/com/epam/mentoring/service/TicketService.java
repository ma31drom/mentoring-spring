package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Ticket;
import com.epam.mentoring.dao.model.User;

public interface TicketService {

	boolean saveTicket(Ticket ticket);

	boolean deleteTicket(Ticket ticket);

	List<Ticket> getUserTickets(User user);

	List<Ticket> getTicketsForFlight(Flight flight);

}
