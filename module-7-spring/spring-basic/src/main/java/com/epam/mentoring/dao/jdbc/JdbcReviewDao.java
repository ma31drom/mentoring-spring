package com.epam.mentoring.dao.jdbc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.epam.mentoring.dao.ReviewDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.User;

@Repository
public class JdbcReviewDao implements ReviewDao {

	@Autowired
	private JdbcTemplate template;

	private final static String SELECT_NO_CONDITION = "SELECT * FROM \"REVIEW\"";
	private final static String USER_CONDITION = " WHERE (\"USER_ID\"=?)";
	private final static String FLIGHT_CONDITION = " WHERE (\"FLIGHT_ID\"=?)";
	private final static String INSERT = "INSERT INTO \"REVIEW\" (\"FLIGHT_ID\", \"USER_ID\", \"POST_DATE\", \"HEADER\", \"REVIEW\") VALUES (?,?,?,?,?)";
	private final static String UPDATE = "UPDATE \"REVIEW\" SET \"FLIGHT_ID\"=?, \"USER_ID\"=?, \"POST_DATE\"=?, \"HEADER\"=?, \"REVIEW\"=? WHERE \"ID\"=?";
	private final static String DELETE = "DELETE FROM \"REVIEW\" WHERE ID = ?";

	@Override
	public List<Review> getReviewsByUser(final User user) {
		return template.query(SELECT_NO_CONDITION + USER_CONDITION, new Object[] { user.getId() },
				new BeanPropertyRowMapper<Review>(Review.class));
	}

	@Override
	public List<Review> getReviewsByFlightId(final Flight flight) {
		return template.query(SELECT_NO_CONDITION + FLIGHT_CONDITION, new Object[] { flight.getId() },
				new BeanPropertyRowMapper<Review>(Review.class));
	}

	@Override
	public boolean saveReview(final Review review) {
		return 1 == template.update(INSERT, new Object[] { review.getFlightId(), review.getUserId(),
				review.getPostDate(), review.getHeader(), review.getReview() });
	}

	@Override
	public boolean updateReview(final Review review) {
		return 1 == template.update(UPDATE, new Object[] { review.getFlightId(), review.getUserId(),
				review.getPostDate(), review.getHeader(), review.getReview(), review.getId() });
	}

	@Override
	public boolean deleteReview(final Review review) {
		return 1 == template.update(DELETE, new Object[] { review.getId() });

	}

	@Override
	public Integer saveReviews(final List<Review> reviews) {
		Integer result = 0;
		for (final int[] i : Arrays.asList(template.batchUpdate(INSERT, reviews.stream().map(review -> {
			return new Object[] { review.getFlightId(), review.getUserId(), review.getPostDate(), review.getHeader(),
					review.getReview() };
		}).collect(Collectors.toList())))) {
			result += IntStream.of(i).sum();
		}
		return result;
	}

}
