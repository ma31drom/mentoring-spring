package com.epam.mentoring.dao;

import java.util.List;

import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.User;

public interface ReviewDao {

	boolean saveReview(Review review);

	Integer saveReviews(List<Review> review);

	boolean updateReview(Review review);

	boolean deleteReview(Review review);

	List<Review> getReviewsByUser(User user);

	List<Review> getReviewsByFlightId(Flight flight);
}
