package co.com.skandia.services.contract.model;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.stefanini.osgi.services.jersey.api.JerseyObject;

public class CreateContractRequest implements JerseyObject {
	private String id;
	private String firstName;
	private String lastName;
	private String document;
	private String typeDocument;
	private String ipClient;
	private String numberPhone;
	private String numberMobile;
	private String residenceAddress;
	private String residencePleace;
	private String email;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getIpClient() {
		return ipClient;
	}

	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getNumberMobile() {
		return numberMobile;
	}

	public void setNumberMobile(String numberMobile) {
		this.numberMobile = numberMobile;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getResidencePleace() {
		return residencePleace;
	}

	public void setResidencePleace(String residencePleace) {
		this.residencePleace = residencePleace;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void fetchByString(String jsonString) {
		JSONObject jsObject;
		try {
			jsObject = JSONFactoryUtil.createJSONObject(jsonString);
			id= jsObject.getString("Id");
			firstName= jsObject.getString("FirstName");
			lastName= jsObject.getString("LastName");
			document= jsObject.getString("Document");
			typeDocument= jsObject.getString("TypeDocument");
			ipClient= jsObject.getString("IpClient");
			numberPhone= jsObject.getString("NumberPhone");
			numberMobile= jsObject.getString("NumberMobile");
			residenceAddress= jsObject.getString("ResidenceAddress");
			residencePleace= jsObject.getString("ResidencePleace");
			email= jsObject.getString("Email");
		} catch (JSONException e) {
			jsObject = JSONFactoryUtil.createJSONObject();
		}

		
	}

	@Override
	public String getJsonString() {
		JSONObject jsObject = JSONFactoryUtil.createJSONObject();

		jsObject.put("Id", id);
		jsObject.put("FirstName", firstName);
		jsObject.put("LastName", lastName);
		jsObject.put("Document", document);
		jsObject.put("TypeDocument", typeDocument);
		jsObject.put("IpClient", ipClient);
		jsObject.put("NumberPhone", numberPhone);
		jsObject.put("NumberMobile", numberMobile);
		jsObject.put("ResidenceAddress", residenceAddress);
		jsObject.put("ResidencePleace", residencePleace);
		jsObject.put("Email", email);
		
		return jsObject.toJSONString();
	}

}
