package com.tanaka.microservices.springcloudconfigservicer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServicerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServicerApplication.class, args);
	}

}
