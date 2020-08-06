package com.stefanini.osgi.services.jersey.api.error;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.stefanini.osgi.services.jersey.api.JerseyObject;

public class ErrorObject implements JerseyObject{

	private int errorCode;
	private String message;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void fetchByString(String jsonString){
		try {
			JSONObject jsObject = JSONFactoryUtil.createJSONObject(jsonString);
			errorCode = jsObject.getInt("errorCode");
			message= jsObject.getString("message");
		}catch (Exception e) {
			message= "";
		}
	}

	@Override
	public String getJsonString() {
		JSONObject jsObject = JSONFactoryUtil.createJSONObject();
		jsObject.put("errorCode", errorCode);
		jsObject.put("message", message);		
		return jsObject.toJSONString();
	}

}
