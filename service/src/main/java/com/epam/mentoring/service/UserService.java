package com.epam.mentoring.service;

import java.util.Date;
import java.util.List;

import com.epam.mentoring.dao.model.User;

public interface UserService extends SimpleCrudService<User> {

	User getUser(String login, String password);

	List<User> getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(Integer reviewCount, Date startFlightDate,
			Date finishFlightDate, Integer ticketCount);

}
