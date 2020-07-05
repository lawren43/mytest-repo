import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

@ContextConfiguration("file:src/test/resources/spring-test-client.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class WsTestCase1 {

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
		// 指定Jaxb方案實現類。spring提供Jaxb2Marshaller
		webServiceTemplate.setMarshaller(marshaller);
		webServiceTemplate.setUnmarshaller(marshaller);

		// 查詢Country
		GetCountryRequest getCountryRequest = new GetCountryRequest();
		getCountryRequest.setName("Spain");
		GetCountryResponse getCountryResponse = (GetCountryResponse) webServiceTemplate.marshalSendAndReceive(getCountryRequest);
		System.out.println("getCountryResponse.getCountry().getName():" + getCountryResponse.getCountry().getName());
		Assert.assertEquals(getCountryResponse.getCountry().getName(), "Spain");

		// 儲存Country
		Country country = new Country();
		country.setName("中國");
		country.setCapital("北京");
		country.setPopulation(1400000000);
		SaveCountryRequest saveCountryRequest = new SaveCountryRequest();
		saveCountryRequest.setCountry(country);
		SaveCountryResponse saveCountryResponse = (SaveCountryResponse) webServiceTemplate.marshalSendAndReceive(saveCountryRequest);
		System.out.println("saveCountryResponse.getName():" + saveCountryResponse.getName());
		Assert.assertEquals(saveCountryResponse.getName(), "中國");

	}

}
