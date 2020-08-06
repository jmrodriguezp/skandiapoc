package com.stefanini.osgi.services.jersey.api.error;

public class JerseyRestExceptionProvider {
	public static JerseyRestException generateIvalidReponse(int errorCode,String message) {
		ErrorObject error = new ErrorObject();
		error.setErrorCode(errorCode);
		error.setMessage(message);
		return new JerseyRestException(error.getJsonString());
	}
}
