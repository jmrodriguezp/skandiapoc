package skandia.web.test.portlet;

import skandia.web.test.constants.SkandiaWebTestPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import co.com.skandia.services.contract.api.SkandiaContractApi;

/**
 * @author jrodriguez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Skandia",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=SkandiaWebTest",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SkandiaWebTestPortletKeys.SKANDIAWEBTEST,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SkandiaWebTestPortlet extends MVCPortlet {

	@Reference
	SkandiaContractApi contractApi;
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
	//	contractApi.createContract();
		super.doView(renderRequest, renderResponse);
	}
	
}