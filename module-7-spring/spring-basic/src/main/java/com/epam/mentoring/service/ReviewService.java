package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.User;

public interface ReviewService {

	boolean saveReview(Review review);

	boolean updateReview(Review review);

	boolean deleteReview(Review review);

	List<Review> getReviewsByUser(User user);

	List<Review> getReviewsForFlight(Flight flight);

}
