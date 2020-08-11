package com.stefanini.osgi.services.jersey.impl;


import com.stefanini.osgi.services.jersey.api.JerseyRestClientApi;
import com.stefanini.osgi.services.jersey.api.error.JerseyRestException;
import com.stefanini.osgi.services.jersey.api.error.JerseyRestExceptionProvider;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.osgi.service.component.annotations.Component;

/**
 * @author jrodriguez
 */
@Component(
	immediate = true,
	property = {
	},
	service = JerseyRestClientApi.class
)
public class JerseyRestClientService  implements JerseyRestClientApi{

	@Override
	public String postBasicAuthentication(String urlService,Object requestObject, Map<String, Object> headers,String user,String password ) throws JerseyRestException {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(user, password);
		Client client = ClientBuilder.newClient();
		client.register(feature);
		WebTarget webTarget = client.target(urlService);
		Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON);
		createHeaders(headers, invocationBuilder);
		Response response  = invocationBuilder.post(Entity.entity(requestObject, MediaType.APPLICATION_JSON));
		if(response.getStatus()==200  ) {
			String s=response.readEntity(String.class);
			return s;
		}else {
			throw JerseyRestExceptionProvider.generateIvalidReponse(response.getStatus(), response.readEntity(String.class));
		}
	}
	
	public void createHeaders( Map<String, Object> headers,Builder invocationBuilder) {
		for (Entry<String, Object> e : headers.entrySet() ) {
			invocationBuilder.header(e.getKey(), e.getValue());
		}
	}

}