package com.stefanini.osgi.services.jersey.impl;


import com.skandia.trasaccion.log.api.TransaccionLogApi;
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
import org.osgi.service.component.annotations.Reference;

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

	@Reference
	private TransaccionLogApi transaccionLog;
	
	@Override
	public String postBasicAuthentication(String urlService,Object requestObject, Map<String, Object> headers,String user,String password ) throws JerseyRestException {
		transaccionLog.info("consumiendo servicio:"+urlService);
		transaccionLog.info("parametro de consumo:"+requestObject);
		transaccionLog.info("headers:"+headers);
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(user, password);
		Client client = ClientBuilder.newClient();
		client.register(feature);
		WebTarget webTarget = client.target(urlService);
		Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON);
		createHeaders(headers, invocationBuilder);
		Response response  = invocationBuilder.post(Entity.entity(requestObject, MediaType.APPLICATION_JSON));
		transaccionLog.info("Condigo de respuesta:"+response.getStatus());
		if(response.getStatus()==200  ) {
			String s=response.readEntity(String.class);
			transaccionLog.info("consumo exitoso:"+s);
			
			return s;
		}else {
			transaccionLog.info("consumo fallido:"+response.readEntity(String.class));
			throw JerseyRestExceptionProvider.generateIvalidReponse(response.getStatus(), response.readEntity(String.class));
		}
	}
	
	public void createHeaders( Map<String, Object> headers,Builder invocationBuilder) {
		for (Entry<String, Object> e : headers.entrySet() ) {
			invocationBuilder.header(e.getKey(), e.getValue());
		}
	}

}