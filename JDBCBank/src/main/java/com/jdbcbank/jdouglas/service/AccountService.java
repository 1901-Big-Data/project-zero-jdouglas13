package com.jdbcbank.jdouglas.service;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import com.jdbcbank.jdouglas.dao.AccountDao;
import com.jdbcbank.jdouglas.dao.AccountOracle;
import com.jdbcbank.jdouglas.model.Account;

public class AccountService {

	private static AccountService service;
	private static AccountDao dao = AccountOracle.getDao();
	
	private AccountService() {
		
	}
	
	public static AccountService getService() {
		if(service == null) {
			service = new AccountService();
		}
		
		return service;
	}
	
	public Optional<List<Account>> view_accounts(String firstName, String lastName){
		return dao.view_accounts(firstName, lastName);
	}
}
