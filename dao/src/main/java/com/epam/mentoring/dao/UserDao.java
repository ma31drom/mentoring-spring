package com.epam.mentoring.dao;

import java.sql.Timestamp;
import java.util.List;

import com.epam.mentoring.dao.model.User;

public interface UserDao extends CrudOpertaions<User> {

	User getUser(String login, String password);

	List<User> getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(Integer reviewCount, Timestamp startFlightDate,
			Timestamp finishFlightDate, Integer ticketCount);

}
