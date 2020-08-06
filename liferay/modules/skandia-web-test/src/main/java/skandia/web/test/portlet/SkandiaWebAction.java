package skandia.web.test.portlet;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import co.com.skandia.services.contract.api.SkandiaContractApi;
import skandia.web.test.constants.SkandiaWebTestPortletKeys;

@Component(immediate = true,
	property = { 
			"javax.portlet.name=" + SkandiaWebTestPortletKeys.SKANDIAWEBTEST,
			"mvc.command.name=createContract" 
	}, 
	service = MVCActionCommand.class)
public class SkandiaWebAction extends BaseMVCActionCommand {

	
	@Reference
	SkandiaContractApi contractApi;
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		User user = (User) actionRequest.getAttribute(WebKeys.USER);
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
		
		actionRequest.setAttribute("GREETER_MESSAGE", respuesta);
		SessionMessages.add(actionRequest, "greetingMessage", respuesta);
	}

}
