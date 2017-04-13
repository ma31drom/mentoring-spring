package com.epam.mentoring;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.epam.mentoring.dao.model.User;
import com.epam.mentoring.service.ReviewService;
import com.epam.mentoring.service.TicketService;
import com.epam.mentoring.service.UserService;

import static org.testng.Assert.*;

@Profile("test")
@ContextConfiguration(classes = { AppConfig.class })
public class UserServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserService userService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private ReviewService reviewService;

	@Test
	public void dbInitTest() {
		final List<User> usersWithReviewsCountInTimeRangeWhoBothTicketsCount = userService
				.getUsersWithReviewsCountInTimeRangeWhoBothTicketsCount(10, new Date(29800000l * 1000),
						new Date(29800000l * 2000), 10);

		assertNotNull(usersWithReviewsCountInTimeRangeWhoBothTicketsCount);

		usersWithReviewsCountInTimeRangeWhoBothTicketsCount.forEach(System.out::println);

	}

	@Test
	public void getTest() {
		final User user = userService.getUser(20);
		assertNotNull(user);
		assertNotNull(user.getId());
		assertNotNull(user.getFirstName());
	}

	@Test
	public void getByLoginTest() {
		final User user = userService.getUser(15);
		assertNotNull(user);

		final User user1 = userService.getUser(user.getLogin(), user.getPassword());
		assertEquals(user.getId(), user1.getId());
		assertEquals(user.getFirstName(), user1.getFirstName());
		assertEquals(user.getLastName(), user1.getLastName());
		assertEquals(user.getLogin(), user1.getLogin());
		assertEquals(user.getPassword(), user1.getPassword());

	}

	@Test
	public void updateTest() {
		final User user = userService.getUser(11);
		assertNotNull(user);

		user.setFirstName("test");
		user.setLastName("test1");

		userService.updateUser(user);

		final User user2 = userService.getUser(11);

		assertEquals(user.getId(), user2.getId());
		assertEquals("test", user2.getFirstName());
		assertEquals("test1", user2.getLastName());
		assertEquals(user.getLogin(), user2.getLogin());
	}

	@Test(expectedExceptions = { DataIntegrityViolationException.class })
	public void deleteTest() {
		final User user = userService.getUser(10);
		assertNotNull(user);
		userService.deleteUser(user);
	}

	@Test
	public void deleteWithDependenciesTest() {

		final User user = userService.getUser(30);
		assertNotNull(user);

		reviewService.getReviewsByUser(user).forEach(reviewService::deleteReview);
		ticketService.getUserTickets(user).forEach(ticketService::deleteTicket);

		userService.deleteUser(user);

		assertNull(userService.getUser(30));
	}

}
