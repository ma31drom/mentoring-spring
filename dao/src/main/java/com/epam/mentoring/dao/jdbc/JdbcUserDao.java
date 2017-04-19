package com.epam.mentoring.dao.jdbc;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.epam.mentoring.dao.UserDao;
import com.epam.mentoring.dao.model.User;

@Repository
public class JdbcUserDao extends AbstractCrudDao<User> implements UserDao {

	@Autowired
	private JdbcTemplate template;

	private static final String LOGIN_PASS_CONDITION = " WHERE (\"LOGIN\"=?) AND  (\"PASSWORD\"=?)";
	private static final String COMPLEX_CONDITION = " AS u WHERE (?<(SELECT COUNT(*) FROM \"TICKET\" AS t WHERE t.USER_ID=u.ID)) AND (?<(SELECT COUNT(*) FROM \"REVIEW\" AS r WHERE (r.USER_ID=u.ID) AND (r.POST_DATE BETWEEN ? AND ?)))";

	private static final String USER = "USER";

	@Override
	public User getUser(final String login, final String password) {
		final List<User> query = template.query(selectStringBuilder().append(LOGIN_PASS_CONDITION).toString(),
				new Object[] { login, password }, new BeanPropertyRowMapper<User>(User.class));
		return CollectionUtils.isEmpty(query) ? null : query.get(0);
	}

	@Override
	public List<User> getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(final Integer reviewCount,
			final Timestamp startFlightDate, final Timestamp finishFlightDate, final Integer ticketCount) {
		return template.query(selectStringBuilder().append(COMPLEX_CONDITION).toString(),
				new Object[] { ticketCount, reviewCount, startFlightDate, finishFlightDate },
				new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	protected Class<User> getModelClass() {
		return User.class;
	}

	@Override
	protected String getTableName() {
		return USER;
	}

}
