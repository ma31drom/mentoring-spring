package com.epam.mentoring.dao.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.epam.mentoring.dao.ReviewDao;
import com.epam.mentoring.dao.model.Flight;
import com.epam.mentoring.dao.model.Review;
import com.epam.mentoring.dao.model.User;

@Repository
public class JdbcReviewDao extends AbstractCrudDao<Review> implements ReviewDao {

	@Autowired
	private JdbcTemplate template;

	private static final String USER_CONDITION = " WHERE (\"USER_ID\"=?)";
	private static final String FLIGHT_CONDITION = " WHERE (\"FLIGHT_ID\"=?)";
	private static final String REVIEW = "REVIEW";

	@Override
	public List<Review> getReviewsByUser(final User user) {
		return template.query(selectStringBuilder().append(USER_CONDITION).toString(), new Object[] { user.getId() },
				new BeanPropertyRowMapper<Review>(Review.class));
	}

	@Override
	public List<Review> getReviewsByFlight(final Flight flight) {
		return template.query(selectStringBuilder().append(FLIGHT_CONDITION).toString(),
				new Object[] { flight.getId() }, new BeanPropertyRowMapper<Review>(Review.class));
	}

	@Override
	protected Class<Review> getModelClass() {
		return Review.class;
	}

	@Override
	protected String getTableName() {
		return REVIEW;
	}

}
