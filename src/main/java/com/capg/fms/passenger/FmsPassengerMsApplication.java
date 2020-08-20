package com.capg.fms.passenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/****************************************************************************************************************************
- File Name        : FmsPassengerMsApplication.java 
- Author           : Capgemini 
- Creation Date    : 11/08/2020
- Modified Date    : 16/08/2020
****************************************************************************************************************************/

@SpringBootApplication
@EnableEurekaClient
public class FmsPassengerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmsPassengerMsApplication.class, args);
	}

}
