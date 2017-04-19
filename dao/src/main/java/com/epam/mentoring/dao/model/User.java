package com.epam.mentoring.dao.model;

public class User extends IdAwareModel {

	private String firstName;
	private String lastName;
	private String login;
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + super.getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", login=" + login
				+ "]";
	}

}
