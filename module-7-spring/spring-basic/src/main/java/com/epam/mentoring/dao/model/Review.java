package com.epam.mentoring.dao.model;

import java.sql.Timestamp;

public class Review {

	private Integer id;
	private Integer flightId;
	private Integer userId;
	private Timestamp postDate;
	private String header;
	private String review;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(final Integer flightId) {
		this.flightId = flightId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

	public Timestamp getPostDate() {
		return postDate;
	}

	public void setPostDate(final Timestamp postDate) {
		this.postDate = postDate;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(final String header) {
		this.header = header;
	}

	public String getReview() {
		return review;
	}

	public void setReview(final String review) {
		this.review = review;
	}

}
