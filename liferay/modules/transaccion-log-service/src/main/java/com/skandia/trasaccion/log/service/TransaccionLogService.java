package com.skandia.trasaccion.log.service;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.skandia.trasaccion.log.api.TransaccionLogApi;

import org.osgi.service.component.annotations.Component;

/**
 * @author jrodriguez
 */
@Component(
	immediate = true,
	property = {
		// TODO enter required service properties
	},
	service = TransaccionLogApi.class
)
public class TransaccionLogService implements TransaccionLogApi {

	Log log =LogFactoryUtil.getLog(TransaccionLogService.class);
	
	@Override
	public void info(Object msg) {
		log.info(userInfo() + msg.toString());
	}
	
	@Override
	public void info(Object msg,Throwable t) {
		log.info(userInfo() + msg.toString(), t);
	}
	
	@Override
	public void error(Object msg) {
		log.error(userInfo() + msg.toString());
	}
	
	@Override
	public void error(Object msg,Throwable t) {
		log.error(userInfo() + msg.toString(), t);
	}
	
	
	private String userInfo() {
		try {
			String userIdS = PrincipalThreadLocal.getName();
			Long userId = Long.parseLong(userIdS);
			User user= UserLocalServiceUtil.getUserById(userId);
			
			ExpandoBridge eb = user.getExpandoBridge();
			String userInfo="[";
			userInfo+="usuario:"+user.getFullName();
			userInfo+="|ip:"+user.getLoginIP();
			userInfo+="|typeDocument:"+(String)eb.getAttribute("typeDocument");;
			userInfo+="|document:"+(String)eb.getAttribute("document")+"]";
			return userInfo;
		} catch (Exception e) {
			return "[usuario:anonimo]";
		}
	}

}