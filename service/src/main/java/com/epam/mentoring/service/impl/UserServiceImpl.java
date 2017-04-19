package com.epam.mentoring.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mentoring.dao.CrudOpertaions;
import com.epam.mentoring.dao.UserDao;
import com.epam.mentoring.dao.model.User;
import com.epam.mentoring.service.UserService;

@Service
public class UserServiceImpl extends AbstractCrudService<User> implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public User getUser(final String login, final String password) {
		return dao.getUser(login, password);
	}

	@Override
	public List<User> getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(final Integer reviewCount,
			final Date startFlightDate, final Date finishFlightDate, final Integer ticketCount) {
		return dao.getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(reviewCount,
				new Timestamp(startFlightDate.getTime()), new Timestamp(finishFlightDate.getTime()), ticketCount);
	}

	@Override
	protected CrudOpertaions<User> getDao() {
		return dao;
	}

}
