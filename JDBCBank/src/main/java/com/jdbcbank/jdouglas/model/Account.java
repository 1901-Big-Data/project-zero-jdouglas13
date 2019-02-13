package com.jdbcbank.jdouglas.model;

import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2342440985720911982L;
	private Integer bankAcoountId;
	private Double bankAccountBalance;
	private String firstName;
	private String lastName;
	
	public Account() {
		super();
	}

	public Account(String firstName, String lastName, Integer bankAccountId, Double bankAccountBalance) {
		super();
		this.bankAcoountId = bankAccountId;
		this.bankAccountBalance = bankAccountBalance;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getBankAcoountId() {
		return bankAcoountId;
	}

	public void setBankAcoountId(Integer bankAcoountId) {
		this.bankAcoountId = bankAcoountId;
	}

	public Double getBankAccountBalance() {
		return bankAccountBalance;
	}

	public void setBankAccountBalance(Double bankAccountBalance) {
		this.bankAccountBalance = bankAccountBalance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankAccountBalance == null) ? 0 : bankAccountBalance.hashCode());
		result = prime * result + ((bankAcoountId == null) ? 0 : bankAcoountId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (bankAccountBalance == null) {
			if (other.bankAccountBalance != null)
				return false;
		} else if (!bankAccountBalance.equals(other.bankAccountBalance))
			return false;
		if (bankAcoountId == null) {
			if (other.bankAcoountId != null)
				return false;
		} else if (!bankAcoountId.equals(other.bankAcoountId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	
}
