package io.github.rafaelaperruci.batch_address_consult_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BatchAddressConsultApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchAddressConsultApiApplication.class, args);
	}

}
