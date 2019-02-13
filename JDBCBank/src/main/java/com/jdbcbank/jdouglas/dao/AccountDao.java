package com.jdbcbank.jdouglas.dao;

import java.util.List;
import java.util.Optional;

import com.jdbcbank.jdouglas.model.Account;

public interface AccountDao {
	
	Optional<List<Account>> view_accounts(String firstName, String lastName);

}