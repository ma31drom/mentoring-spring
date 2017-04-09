package com.epam.mentoring.dao.model;

public class Ticket {

	private Integer id;
	private Integer flightId;
	private Integer placeNumber;
	private Integer userId;

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

	public Integer getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(final Integer placeNumber) {
		this.placeNumber = placeNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

}
