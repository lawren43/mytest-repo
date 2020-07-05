import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.springframework.ws.client.core.WebServiceTemplate;

import cn.wannshan.j2ee.ws.dto.Country;
import cn.wannshan.j2ee.ws.dto.GetCountryRequest;
import cn.wannshan.j2ee.ws.dto.GetCountryResponse;
import cn.wannshan.j2ee.ws.dto.SaveCountryRequest;
import cn.wannshan.j2ee.ws.dto.SaveCountryResponse;

/**
 * 
 */

/**
 * @author lawre
 *
 */

public class WsTestCase2 {

	@Autowired
	WebServiceTemplate webServiceTemplate;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// 這是個包名，是你利用maven外掛根據xsd檔案生成pojo類，存放包名
		marshaller.setContextPath("cn.wannshan.j2ee.ws.dto");

		QueryCountryClient client = new QueryCountryClient();
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);

		GetCountryResponse response = client.getCountryDetails("Spain");
		System.out.println("Country : " + response.getCountry().getName());
		System.out.println("Capital : " + response.getCountry().getCapital());
		System.out.println("Population : " + response.getCountry().getPopulation());

	}

}
