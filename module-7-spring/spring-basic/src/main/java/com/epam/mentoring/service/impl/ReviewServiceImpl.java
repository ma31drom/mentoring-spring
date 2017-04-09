package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mentoring.dao.ReviewDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.User;
import com.epam.mentoring.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao dao;

	@Override
	public boolean saveReview(final Review review) {
		return dao.saveReview(review);
	}

	@Override
	public boolean updateReview(final Review review) {
		return dao.updateReview(review);
	}

	@Override
	public boolean deleteReview(final Review review) {
		return dao.deleteReview(review);
	}

	@Override
	public List<Review> getReviewsByUser(final User user) {
		return dao.getReviewsByUser(user);
	}

	@Override
	public List<Review> getReviewsForFlight(final Flight flight) {
		return dao.getReviewsByFlightId(flight);
	}

}
