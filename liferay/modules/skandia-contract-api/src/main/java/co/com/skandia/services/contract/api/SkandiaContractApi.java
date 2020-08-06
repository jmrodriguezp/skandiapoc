package co.com.skandia.services.contract.api;

/**
 * @author jrodriguez
 */
public interface SkandiaContractApi {


	public String createContract(String id, String firstName, String lastName, String document, String typeDocument, String ip,
			String numberPhone, String numberMobile, String residenceAddress, String residencePleace, String email);
}