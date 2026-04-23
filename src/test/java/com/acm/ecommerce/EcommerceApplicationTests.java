package com.acm.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EntityScan(basePackages = "com.acm.ecommerce.entities")
@EnableJpaRepositories(basePackages = "com.acm.ecommerce.repository")
@ActiveProfiles("test")
class EcommerceApplicationTests {

	@Test
	void contextLoads() {
	}

}
