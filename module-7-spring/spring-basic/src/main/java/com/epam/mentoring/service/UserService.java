package com.epam.mentoring.service;

import java.util.Date;
import java.util.List;

import com.epam.mentoring.dao.model.User;

public interface UserService {

	User getUser(String login, String password);

	User getUser(Integer id);

	boolean createUser(User user);

	boolean deleteUser(User user);

	boolean updateUser(User user);

	List<User> getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(Integer reviewCount, Date startFlightDate,
			Date finishFlightDate, Integer ticketCount);

}
