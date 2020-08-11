package co.com.skandia.services.contract.resources;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import co.com.skandia.services.contract.api.SkandiaContractApi;


@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=formularioskandia",
	        "mvc.command.name=addContract"
	    },
	    service = MVCResourceCommand.class
	)
public class SkandiaContractResource implements MVCResourceCommand{

	@Reference
	SkandiaContractApi contractApi;
	
	Log log = LogFactoryUtil.getLog(SkandiaContractResource.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		User user = (User) resourceRequest.getAttribute(WebKeys.USER);
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
		String respuesta=contractApi.createContract(id, firstName, lastName, document, typeDocument, ip, numberPhone, numberMobile, residenceAddress, residencePleace, email);
		try {
			log.info("respuesta:"+respuesta);
			resourceResponse.getWriter().write(respuesta);
			
		} catch (IOException e) {
			log.error(e.getMessage());
			log.error(e);
		}
		return false;
	}

}
