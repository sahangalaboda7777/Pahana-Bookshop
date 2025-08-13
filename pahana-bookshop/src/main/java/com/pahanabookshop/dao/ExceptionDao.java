package com.pahanabookshop.dao;

public class ExceptionDao extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExceptionDao(Throwable cause) { super(cause); }
}
