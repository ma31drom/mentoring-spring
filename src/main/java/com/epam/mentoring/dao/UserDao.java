package com.epam.mentoring.dao;

import java.sql.Timestamp;
import java.util.List;

import com.epam.mentoring.dao.model.User;

public interface UserDao {

	User getUser(String login, String password);

	User getUser(Integer id);

	boolean createUser(User user);

	Integer createUsers(List<User> users);

	boolean deleteUser(User user);

	boolean updateUser(User user);

	List<User> getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(Integer reviewCount, Timestamp startFlightDate,
			Timestamp finishFlightDate, Integer ticketCount);

}
