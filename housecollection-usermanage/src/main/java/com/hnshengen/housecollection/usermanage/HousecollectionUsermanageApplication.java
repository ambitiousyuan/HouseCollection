package com.hnshengen.housecollection.usermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hnshengen.housecollection.usermanage.mapper.UserInfoMapper")
public class HousecollectionUsermanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousecollectionUsermanageApplication.class, args);
	}

}
