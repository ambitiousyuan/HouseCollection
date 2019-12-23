package com.hnshengen.housecollection.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.hnshengen.housecollection")
public class HousecollectionHouseWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousecollectionHouseWebApplication.class, args);
	}

}
