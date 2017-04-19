package com.epam.mentoring.dao;

import java.util.List;

import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Ticket;
import com.epam.mentoring.dao.model.User;

public interface TicketDao extends CrudOpertaions<Ticket> {

	List<Ticket> getUserTickets(User user);

	List<Ticket> getTicketsForFlight(Flight flight);

}