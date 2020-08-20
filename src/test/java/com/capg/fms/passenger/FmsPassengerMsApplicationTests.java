package com.capg.fms.passenger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.capg.fms.passenger.exceptions.InvalidInputException;
import com.capg.fms.passenger.model.Passenger;
import com.capg.fms.passenger.repository.IPassengerRepo;
import com.capg.fms.passenger.service.IPassengerService;

/****************************************************************************************************************************
- File Name        : FmsPassengerMsAppplication.java 
- Author           : Capgemini 
- Creation Date    : 11/08/2020
- Modified Date    : 17/08/2020
****************************************************************************************************************************/

@SpringBootTest
class FmsPassengerMsApplicationTests {

	@Autowired
	IPassengerService passengerService;

	@Autowired
	IPassengerRepo passengerRepo;

	/****************************************************************************************************************************
	- Test Name      : addPassengerTest
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether data is added to database or not
	****************************************************************************************************************************/

	@Test
	public void addPassengerTest() throws URISyntaxException 
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8989/passenger/add";
		URI uri = new URI(baseUrl);
		Passenger passenger = new Passenger();
		passenger.setPassengerNum(9959369426l);
		passenger.setPassengerName("Srinidhi");
		passenger.setPassengerAge(21);
		passenger.setPassengerUIN(789456123214l);
		passenger.setLuggage(1);
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		HttpEntity<Passenger> request = new HttpEntity<>(passenger, headers);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertNotNull(passenger);
	}

	/****************************************************************************************************************************
	- Test Name      : deletePassengerTest
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether data is deleted from database or not
	****************************************************************************************************************************/

	@Test
	public void deletePassengerTest() throws URISyntaxException 
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8989/passenger/delete/num/9290562277";
		URI uri = new URI(baseUrl);
		
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	/****************************************************************************************************************************
	- Test Name      : getPassengerTest
	- Author         : Capgemini 
	- Creation Date  : 17/08/2020
	- Description    : This test is used to check whether data is retrieved from database or not
	****************************************************************************************************************************/
	
	@Test
	public void getPassengerTest() throws URISyntaxException 
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8989/passenger/num/7329802982";
		URI uri = new URI(baseUrl);

		ResponseEntity<Passenger> result = restTemplate.getForEntity(uri, Passenger.class);
		Passenger data = result.getBody();
		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertNotNull(data);
	}

	/****************************************************************************************************************************
	- Test Name      : getAllPassengersTest
	- Author         : Capgemini 
	- Creation Date  : 17/08/2020
	- Description    : This test is used to check whether Passenger List is retrieved from database or not
	****************************************************************************************************************************/

	@Test
	public void getAllPassengersTest() throws URISyntaxException 
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8989/passenger/all";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertEquals(true, result.getBody().contains("PassengerList"));
	}

	/****************************************************************************************************************************
	- Test Name      : updatePassengerTest
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger details are updated in database or not
	****************************************************************************************************************************/

	@Test
	public void updatePassengerTest() throws URISyntaxException 
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8989/passenger/update";
		URI uri = new URI(baseUrl);
		Passenger passenger = new Passenger();
		passenger.setPassengerNum(6300057247l);
		passenger.setPassengerName("Kalpana");
		passenger.setPassengerAge(21);
		passenger.setPassengerUIN(789456123214l);
		passenger.setLuggage(1);
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		HttpEntity<Passenger> request = new HttpEntity<>(passenger, headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertNotNull(passenger);

	}

	/****************************************************************************************************************************
	- Test Name      : testValidPassengerNumber
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger Number is Valid or not
	****************************************************************************************************************************/
	
	@Test
	public void testValidPassengerNumber() throws InvalidInputException {
		assertEquals(true, passengerService.validatePassengerNumber(9959369426l));
	}
	
	/****************************************************************************************************************************
	- Test Name      : testValidPassengerUIN
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger UIN is Valid or not
	****************************************************************************************************************************/
	
	@Test
	public void testValidPassengerUIN() throws InvalidInputException {
		assertEquals(true, passengerService.validatePassengerUIN(123456789012L));
	}
	
	/****************************************************************************************************************************
	- Test Name      : testValidPassengerName
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger Name is Valid or not
	****************************************************************************************************************************/
	
	@Test
	public void testValidatePassengerName() throws InvalidInputException {
		assertEquals(true, passengerService.validatePassengerName("Srinidhi"));
	}
	
	/****************************************************************************************************************************
	- Test Name      : testValidPassengerAge
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger Age is Valid or not
	****************************************************************************************************************************/
	
	@Test
	public void testValidatePassengeAge() throws InvalidInputException {
		assertEquals(true, passengerService.validatePassengerAge(21));
	}
	
	/****************************************************************************************************************************
	- Test Name      : testValidPassengerLuggage
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger Luggage is Valid or not
	****************************************************************************************************************************/
	
	@Test
	public void testValidatePassengerLuggage() throws InvalidInputException {
		assertEquals(true, passengerService.validateLuggage(2));
	}
	
	/****************************************************************************************************************************
	- Test Name      : testValidPassengerNumberWithLessDigits
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger Number is Valid or not when we enter digits less than 10
	****************************************************************************************************************************/
	
	@Test
	public void testPassengerNumberWithLessDigits() throws InvalidInputException {

		Exception exception = assertThrows(InvalidInputException.class, () -> {
			passengerService.validatePassengerNumber(4567890L);
		});

		String expectedMessage = "Passenger number should be of 10 digits";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	/****************************************************************************************************************************
	- Test Name      : testValidPassengerNumberWithMoreDigits
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger Number is Valid or not when we enter digits more than 10
	****************************************************************************************************************************/
	
	@Test
	public void testPassengerNumberWithMoreDigits() throws InvalidInputException {

		Exception exception = assertThrows(InvalidInputException.class, () -> {
			passengerService.validatePassengerNumber(4567890123123L);
		});

		String expectedMessage = "Passenger number should be of 10 digits";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	/****************************************************************************************************************************
	- Test Name      : testValidPassengerUINWithLessDigits
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger UIN is Valid or not when we enter digits less than 12
	****************************************************************************************************************************/
	
	@Test
	public void testPassengerUINWithLessDigits() throws InvalidInputException {

		Exception exception = assertThrows(InvalidInputException.class, () -> {
			passengerService.validatePassengerUIN(4567890L);
		});

		String expectedMessage = "Passenger UIN should be of 12 digits";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	/****************************************************************************************************************************
	- Test Name      : testValidPassengerUINWithMoreDigits
	- Author         : Capgemini 
	- Creation Date  : 16/08/2020
	- Description    : This test is used to check whether Passenger UIN is Valid or not when we enter digits more than 12
	****************************************************************************************************************************/
	
	@Test
	public void testPassengerUINWithMoreDigits() throws InvalidInputException {

		Exception exception = assertThrows(InvalidInputException.class, () -> {
			passengerService.validatePassengerUIN(1234345678907856L);
		});

		String expectedMessage = "Passenger UIN should be of 12 digits";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
