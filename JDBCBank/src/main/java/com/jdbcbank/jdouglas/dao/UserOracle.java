package com.jdbcbank.jdouglas.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

import com.jdbcbank.jdouglas.model.User;
import com.jdbcbank.jdouglas.util.ConnectionUtil;

public class UserOracle implements UserDao {

	private static UserOracle instance;
	//private static final Logger log = LogManager.getLogger(UserOracle.class);
	
	private UserOracle() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserDao getDao() {
		if(instance == null) {
			instance = new UserOracle();
		}
		
		return instance;
	}
	
	@Override
	public Optional<User> attempt_login(String username, String password) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		
		if (con == null) {
			return Optional.empty();
		}
		
		try {
			String sql = "call attempt_login(?,?,?,?,?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.registerOutParameter(5, Types.INTEGER);
			cs.execute();
			
			Integer success = cs.getInt(3);
			Integer id = cs.getInt(4);
			Integer admin = cs.getInt(5);
			
			if (success == 0) {
				throw new Exception("Invalid username / password combination");
			}
			
			User user = new User(id, username, "", (admin > 0) ? true : false);
			return Optional.of(user);
		} catch (SQLException e) {
			System.out.println("database problem");
			//log.error("problem with database {}", e);
			return Optional.empty();
		}
	}

	@Override						
	public Optional<User> add_user(String firstname, String mi, String lastname,String username, String password, Boolean isAdmin) {
		Connection con = ConnectionUtil.getConnection();
		
		if (con == null) {
			return Optional.empty();
		}
		
		try {				 
			String sql = "call add_user(?,?,?,?,?,?, ?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, firstname);
			cs.setString(2, mi);
			cs.setString(3, lastname);
			cs.setString(4, username);
			cs.setString(5, password);
			cs.setInt(6, (isAdmin) ? 1 : 0);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.execute();
			
						
			Integer id = cs.getInt(7);
			
			User user = new User(id, firstname, mi, lastname, username, "", isAdmin);
			
			return Optional.of(user);
		} catch (SQLException e) {
			return Optional.empty();
		}
	}


}
