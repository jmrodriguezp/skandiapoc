package co.com.skandia.services.contract.service;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.skandia.trasaccion.log.api.TransaccionLogApi;
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
			"json.web.service.context.name=skandiaJsonService",
			"json.web.service.context.path=Contract"
	},
	service = SkandiaContractApi.class
)
@AccessControlled
@JSONWebService
public class SkandiaContractService implements SkandiaContractApi {
	
	@Reference
	private JerseyRestClientApi restClient;
	
	@Reference
	private TransaccionLogApi transaccionLog;
	
	@Override
	public String createContract(String id, String firstName, String lastName, String document, String typeDocument, String ip, String numberPhone, String numberMobile, String residenceAddress, String residencePleace, String email) {
		
		transaccionLog.info("Creando contrato");
		
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
			String reponse=restClient.postBasicAuthentication(urlService, request.getJsonString(), headers, user, password);
			transaccionLog.info("Contrato creado correctamente");
			return reponse;
			
		} catch (JerseyRestException e) {
			transaccionLog.info("Fallo al crear elcontrato",e);
			return e.getMessage();
		}
	}
	
	@Override
	public String createContractByUser() {
		try {
			String userIdS = PrincipalThreadLocal.getName();
			Long userId = Long.parseLong(userIdS);
			User user= UserLocalServiceUtil.getUserById(userId);
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String ip = user.getLoginIP();
			String email = user.getEmailAddress();
			ExpandoBridge eb = user.getExpandoBridge();
			String id = (String)eb.getAttribute("id");
			String document = (String)eb.getAttribute("document");
			String typeDocument = (String)eb.getAttribute("typeDocument");
			String numberPhone = (String)eb.getAttribute("numberPhone");
			String numberMobile = (String)eb.getAttribute("numberMobile");
			String residenceAddress = (String)eb.getAttribute("residenceAddress");
			String residencePleace = (String)eb.getAttribute("residencePleace");
			return this.createContract(id, firstName, lastName, document, typeDocument, ip, numberPhone, numberMobile, residenceAddress, residencePleace, email);
		} catch (Exception e) {
			return e.getMessage();
		}
	
	}

}