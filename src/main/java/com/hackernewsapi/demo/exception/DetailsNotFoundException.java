package com.hackernewsapi.demo.exception;

import org.springframework.http.HttpStatus;

public class DetailsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DetailsNotFoundException(HttpStatus status) {

	}

}
