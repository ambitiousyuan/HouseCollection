package com.hnshengen.housecollection.usermanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hnshengen.housecollection.usermanage.mapper")
//@ComponentScan(basePackages = "com.hnshengen.housecollection")
public class HousecollectionUsermanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousecollectionUsermanageApplication.class, args);
	}

}
