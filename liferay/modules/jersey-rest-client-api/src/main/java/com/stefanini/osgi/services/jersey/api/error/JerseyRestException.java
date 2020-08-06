package com.stefanini.osgi.services.jersey.api.error;

public class JerseyRestException extends Exception {

	public JerseyRestException() {
		super();
	}

	public JerseyRestException(String message) {
		super(message);
	}

	public JerseyRestException(String message, Throwable cause) {
		super(message, cause);
	}

	public JerseyRestException(Throwable cause) {
		super(cause);
	}

}
