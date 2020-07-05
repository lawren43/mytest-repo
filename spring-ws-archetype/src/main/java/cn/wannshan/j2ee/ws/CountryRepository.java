package cn.wannshan.j2ee.ws;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.wannshan.j2ee.ws.dto.Country;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

	private static final Map<String, Country> countries = new HashMap<>();

	@PostConstruct
	public void initData() {
		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setPopulation(46704314);

		countries.put(spain.getName(), spain);

		Country poland = new Country();
		poland.setName("Poland");
		poland.setCapital("Warsaw");

		poland.setPopulation(38186860);

		countries.put(poland.getName(), poland);

		Country uk = new Country();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setPopulation(63705000);

		countries.put(uk.getName(), uk);
	}

	public String putCounttry(Country country) {
		Assert.notNull(country, "The country must not be null");
		Assert.notNull(country.getName(), "The country's name must not be null");
		countries.put(country.getName(), country);
		return country.getName();
	}

	public Country findCountry(String name) {
		Assert.notNull(name, "The country's name must not be null");
		return countries.get(name);
	}
}
