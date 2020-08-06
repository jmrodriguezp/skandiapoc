package co.com.skandia.services.contract.service;

import com.liferay.portal.kernel.util.PropsUtil;
import com.stefanini.osgi.services.jersey.api.JerseyRestClientApi;
import com.stefanini.osgi.services.jersey.api.error.JerseyRestException;

import java.util.HashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import co.com.skandia.services.contract.api.SkandiaContractApi;
import co.com.skandia.services.contract.model.CreateContractRequest;

/**
 * @author jrodriguez
 */
@Component(
	immediate = true,
	property = {
	},
	service = SkandiaContractApi.class
)
public class SkandiaContractService implements SkandiaContractApi {
	
	@Reference
	private JerseyRestClientApi restClient;
	
	@Override
	public String createContract(String id, String firstName, String lastName, String document, String typeDocument, String ip, String numberPhone, String numberMobile, String residenceAddress, String residencePleace, String email) {
		CreateContractRequest request = new CreateContractRequest();
		request.setId(id);
		request.setFirstName(firstName);
		request.setLastName(lastName);
		request.setDocument(document);
		request.setTypeDocument(typeDocument);
		request.setIpClient(ip);
		request.setNumberPhone(numberPhone);
		request.setNumberMobile(numberMobile);
		request.setResidenceAddress(residenceAddress);
		request.setResidencePleace(residencePleace);
		request.setEmail(email);
		
		String ContractSubscriptionKey = PropsUtil.get("contract-subscription-Key");
		String urlService=PropsUtil.get("contract-service-url");
		String user=PropsUtil.get("contract-user");
		String password=PropsUtil.get("contract-password");
		
		HashMap<String, Object> headers = new HashMap<String, Object>();
		headers.put("Ocp-Apim-Trace", true);
		headers.put("Ocp-Apim-Subscription-Key", ContractSubscriptionKey);
		
		try {
			return restClient.postBasicAuthentication(urlService, request.getJsonString(), headers, user, password);
		} catch (JerseyRestException e) {
			return e.getMessage();
		}
	
	}
	

}