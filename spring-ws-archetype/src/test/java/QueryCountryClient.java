import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import cn.wannshan.j2ee.ws.dto.GetCountryRequest;
import cn.wannshan.j2ee.ws.dto.GetCountryResponse;

public class QueryCountryClient extends WebServiceGatewaySupport {

	public GetCountryResponse getCountryDetails(String country) {
		String uri = "http://localhost:8080/spring-ws-archetype/webservice/queryService";
		GetCountryRequest request = new GetCountryRequest();
		request.setName(country);

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);
		return response;
	}

}
