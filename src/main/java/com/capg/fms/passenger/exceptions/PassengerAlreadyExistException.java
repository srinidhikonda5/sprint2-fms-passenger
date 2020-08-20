
package com.capg.fms.passenger.exceptions;

/****************************************************************************************************************************
- File Name        : PassengerAlreadyExistException.java 
- Author           : Capgemini 
- Creation Date    : 11/08/2020
****************************************************************************************************************************/

public class PassengerAlreadyExistException extends RuntimeException {
	
	public PassengerAlreadyExistException(String message) {
		super(message);
	}
}
