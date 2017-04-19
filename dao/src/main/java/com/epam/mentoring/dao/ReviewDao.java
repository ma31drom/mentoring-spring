package com.epam.mentoring.dao;

import java.util.List;

import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.User;

public interface ReviewDao extends CrudOpertaions<Review> {

	List<Review> getReviewsByUser(User user);

	List<Review> getReviewsByFlight(Flight flight);
}
