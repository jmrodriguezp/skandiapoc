package com.stefanini.osgi.services.jersey.api;


public interface JerseyObject {
	
	public abstract void fetchByString(String jsonString);
	
	public abstract String getJsonString();
}
