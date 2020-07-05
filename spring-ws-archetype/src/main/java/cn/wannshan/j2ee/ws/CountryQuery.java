package cn.wannshan.j2ee.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cn.wannshan.j2ee.ws.dto.GetCountryRequest;
import cn.wannshan.j2ee.ws.dto.GetCountryResponse;
import cn.wannshan.j2ee.ws.dto.SaveCountryRequest;
import cn.wannshan.j2ee.ws.dto.SaveCountryResponse;

@Endpoint
public class CountryQuery {

	private static final String NAMESPACE_URI = "http://j2eeweb.wannshan.cn/hr/schemas";
	// 注入spring bean
	@Autowired
	CountryRepository countryRepository;

	/***
	 * 查詢country
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse queryCountry(@RequestPayload GetCountryRequest request) throws Exception {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));
		return response;
	}

	/**
	 * 儲存country
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveCountryRequest")
	@ResponsePayload
	public SaveCountryResponse saveCountry(@RequestPayload SaveCountryRequest request) throws Exception {
		SaveCountryResponse response = new SaveCountryResponse();
		countryRepository.putCounttry(request.getCountry());
		response.setName(request.getCountry().getName());
		return response;
	}
}
