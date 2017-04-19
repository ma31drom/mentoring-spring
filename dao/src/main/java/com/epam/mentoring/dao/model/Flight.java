package com.epam.mentoring.dao.model;

import java.sql.Timestamp;

public class Flight extends IdAwareModel {

	private String startingEndpointName;
	private String endingEndpointName;
	private Integer distance;
	private Double cost;
	private Timestamp startDate;
	private Integer availableTicketCount;
	private Integer fullTicketCount;

	public Integer getFullTicketCount() {
		return fullTicketCount;
	}

	public void setFullTicketCount(final Integer fullTicketCount) {
		this.fullTicketCount = fullTicketCount;
	}

	public String getStartingEndpointName() {
		return startingEndpointName;
	}

	public void setStartingEndpointName(final String startingEndpointName) {
		this.startingEndpointName = startingEndpointName;
	}

	public String getEndingEndpointName() {
		return endingEndpointName;
	}

	public void setEndingEndpointName(final String endingEndpointName) {
		this.endingEndpointName = endingEndpointName;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(final Integer distance) {
		this.distance = distance;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(final Double cost) {
		this.cost = cost;
	}

	public Integer getAvailableTicketCount() {
		return availableTicketCount;
	}

	public void setAvailableTicketCount(final Integer availableTicketCount) {
		this.availableTicketCount = availableTicketCount;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(final Timestamp startDate) {
		this.startDate = startDate;
	}
}
