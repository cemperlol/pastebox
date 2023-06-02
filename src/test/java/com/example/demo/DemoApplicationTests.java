package com.example.demo;

import com.example.demo.config.ConfigProperties;
import com.example.demo.dto.PasteboxRequest;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.model.pastebox.PasteboxRestrictions;
import com.example.demo.services.pastebox.PasteboxService;
import com.example.demo.services.pastebox.PasteboxServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class DemoApplicationTests {

	private final PasteboxService service;

	private final ConfigProperties config;

	@Autowired
	public DemoApplicationTests(PasteboxServiceImpl service,
								@Qualifier("configProperties") ConfigProperties config) {
		this.service = service;
		this.config = config;
	}

	@Test
	void testAdd() {
		int expectedSize = 1;
		String expectedUrl = config.getHost() + "/0";
		PasteboxRequest request = new PasteboxRequest();

		request.setData("fjlskdjfklds");
		request.setRestriction(PasteboxRestrictions.PUBLIC);
		request.setExpirationTime(1000);

		assertEquals(expectedUrl, service.add(request).url());
		assertEquals(expectedSize, service.getLastLimitedAndPublic().size());
	}

	@Test
	void testGetByHash() {
		String expectedData = "fjlskdjfklds";
		PasteboxRequest request = new PasteboxRequest();

		request.setData("fjlskdjfklds");
		request.setRestriction(PasteboxRestrictions.PUBLIC);
		request.setExpirationTime(1000);

		service.add(request);

		assertEquals(expectedData, service.getByHash(Integer.toHexString(service.getCount() - 1)).data());
	}

	@Test
	void testGetByIncorrectHash() {
		assertThrows(EntityNotFoundException.class, () -> service.getByHash("hfkjsdf"));
	}

	@Test
	void testGetLastLimitedAndPublic() {
		int expectedSize = config.getLimit();
		String data = "hgkjfdhgjkdf";
		Random random = new Random();

		for (int i = 0; i < 100; i++) {
			PasteboxRequest request = new PasteboxRequest();

			request.setData(data);
			request.setRestriction(random.nextInt() % 2 == 0 ?
					PasteboxRestrictions.UNLISTED : PasteboxRestrictions.PUBLIC);
			request.setExpirationTime(1000);

			service.add(request);
		}

		assertEquals(expectedSize, service.getLastLimitedAndPublic().size());
	}
}
