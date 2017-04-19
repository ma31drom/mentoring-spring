package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.User;

public interface ReviewService extends SimpleCrudService<Review> {

	List<Review> getReviewsByUser(User user);

	List<Review> getReviewsForFlight(Flight flight);

}
