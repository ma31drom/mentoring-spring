package com.epam.mentoring.dao.jdbc;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.epam.mentoring.dao.UserDao;
import com.epam.mentoring.dao.model.User;

@Repository
public class JdbcUserDao implements UserDao {
	@Autowired
	private JdbcTemplate template;

	private final static String SELECT_NO_CONDITION = "SELECT * FROM \"USER\"";
	private final static String LOGIN_PASS_CONDITION = " WHERE (\"LOGIN\"=?) AND  (\"PASSWORD\"=?)";
	private final static String ID_CONDITION = " WHERE (\"ID\"=?)";
	private final static String COMPLEX_SELECT = "SELECT * FROM \"USER\" AS u WHERE (?<(SELECT COUNT(*) FROM \"TICKET\" AS t WHERE t.USER_ID=u.ID)) AND (?<(SELECT COUNT(*) FROM \"REVIEW\" AS r WHERE (r.USER_ID=u.ID) AND (r.POST_DATE BETWEEN ? AND ?)))";
	private final static String INSERT = "INSERT INTO \"USER\" (\"FIRST_NAME\", \"LAST_NAME\", \"LOGIN\", \"PASSWORD\") VALUES (?,?,?,?)";
	private final static String UPDATE = "UPDATE \"USER\" SET \"FIRST_NAME\"=?, \"LAST_NAME\"=?, \"PASSWORD\"=? WHERE \"ID\"=?";
	private final static String DELETE = "DELETE FROM \"USER\" WHERE ID = ?";

	@Override
	public User getUser(final String login, final String password) {
		final List<User> query = template.query(SELECT_NO_CONDITION + LOGIN_PASS_CONDITION,
				new Object[] { login, password }, new BeanPropertyRowMapper<User>(User.class));
		return CollectionUtils.isEmpty(query) ? null : query.get(0);
	}

	@Override
	public boolean createUser(final User user) {
		return 1 == template.update(INSERT,
				new Object[] { user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword() });
	}

	@Override
	public boolean deleteUser(final User user) {
		return 1 == template.update(DELETE, new Object[] { user.getId() });
	}

	@Override
	public boolean updateUser(final User user) {
		return 1 == template.update(UPDATE,
				new Object[] { user.getFirstName(), user.getLastName(), user.getPassword(), user.getId() });
	}

	@Override
	public List<User> getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(final Integer reviewCount,
			final Timestamp startFlightDate, final Timestamp finishFlightDate, final Integer ticketCount) {
		return template.query(COMPLEX_SELECT,
				new Object[] { ticketCount, reviewCount, startFlightDate, finishFlightDate },
				new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public User getUser(final Integer id) {
		final List<User> query = template.query(SELECT_NO_CONDITION + ID_CONDITION, new Object[] { id },
				new BeanPropertyRowMapper<User>(User.class));
		return CollectionUtils.isEmpty(query) ? null : query.get(0);
	}

	@Override
	public Integer createUsers(final List<User> users) {
		Integer result = 0;
		for (final int[] i : Arrays.asList(template.batchUpdate(INSERT, users.stream().map(user -> {
			return new Object[] { user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword() };
		}).collect(Collectors.toList())))) {
			result += IntStream.of(i).sum();
		}
		return result;
	}

}
