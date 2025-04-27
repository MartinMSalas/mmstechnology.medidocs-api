package com.medi.docs.medidocsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MedidocsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedidocsApiApplication.class, args);
	}

}
