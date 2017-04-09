package com.epam.mentoring.dao;

import java.util.List;

import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Ticket;
import com.epam.mentoring.dao.model.User;

public interface TicketDao {

	boolean saveTicket(Ticket ticket);

	Integer saveTickets(List<Ticket> tickets);

	List<Ticket> getUserTickets(User user);

	List<Ticket> getTicketsForFlight(Flight flight);

	boolean deleteTicket(Ticket ticket);

}