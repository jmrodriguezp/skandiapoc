package com.stefanini.osgi.services.jersey.api;

import com.stefanini.osgi.services.jersey.api.error.JerseyRestException;

import java.util.Map;

/**
 * @author jrodriguez
 */
public interface JerseyRestClientApi {

	

	public String postBasicAuthentication(String urlService, Object requestObject, Map<String, Object> headers, String user,
			String password) throws JerseyRestException;
}