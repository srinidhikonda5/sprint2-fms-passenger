
package com.capg.fms.passenger.exceptions;

/****************************************************************************************************************************
- File Name        : PassengerNotFoundException.java 
- Author           : Capgemini 
- Creation Date    : 13/08/2020
****************************************************************************************************************************/

public class PassengerNotFoundException extends RuntimeException {
	
	public PassengerNotFoundException(String message) {
		super(message);
	}

}
