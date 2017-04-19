package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mentoring.dao.CrudOpertaions;
import com.epam.mentoring.dao.ReviewDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.User;
import com.epam.mentoring.service.ReviewService;

@Service
public class ReviewServiceImpl extends AbstractCrudService<Review> implements ReviewService {

	@Autowired
	private ReviewDao dao;

	@Override
	public List<Review> getReviewsByUser(final User user) {
		return dao.getReviewsByUser(user);
	}

	@Override
	public List<Review> getReviewsForFlight(final Flight flight) {
		return dao.getReviewsByFlight(flight);
	}

	@Override
	protected CrudOpertaions<Review> getDao() {
		return dao;
	}

}
