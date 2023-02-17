package com.douzone.jblog.exception;

import java.sql.SQLException;

public class UserRepositoryException extends RuntimeException {

	public UserRepositoryException() {
		super("UserRepositoryException Occurs");
	}

	public UserRepositoryException(SQLException e) {
		super(e);
	}
}
