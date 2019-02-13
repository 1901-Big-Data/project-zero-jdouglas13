package com.jdbcbank.jdouglas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jdbcbank.jdouglas.model.Account;
import com.jdbcbank.jdouglas.util.ConnectionUtil;

public class AccountOracle implements AccountDao {
	
	private static AccountOracle instance;
	
	private AccountOracle() {
		
	}
	
	public static AccountDao getDao() {
		if(instance == null) {
			instance = new AccountOracle();
		}
		
		return instance;
	}

	public Optional<List<Account>> view_accounts(String firstName, String lastName) {
		
		Connection con = ConnectionUtil.getConnection();
		
		if (con == null) {
			return Optional.empty();
		}
		
		String sqlString = "SELECT Users.firstname, Users.lastname, Bank_Accounts.bank_account_id, Bank_Accounts.account_balance " + 
						   "FROM users " + 
						   "RIGHT JOIN bank_accounts " + 
						   "ON Bank_accounts.user_id = users.user_id " + 
						   "WHERE users.firstname = '" + firstName + "' AND users.lastname = '" + lastName + 
						   "' ORDER BY users.lastname";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sqlString);
			ResultSet result = stmt.executeQuery();
			
			List<Account> bankAccounts = new ArrayList<Account>();
			
			while (result.next()) {
				
				bankAccounts.add(new Account(result.getString(1), result.getString(2), result.getInt(3), result.getDouble(4)));
			
			}
			
			return Optional.of(bankAccounts);
			
		} catch (SQLException e) {
			System.out.println(e);
	        e.printStackTrace();
		}
		
		return Optional.empty();
	}

}
