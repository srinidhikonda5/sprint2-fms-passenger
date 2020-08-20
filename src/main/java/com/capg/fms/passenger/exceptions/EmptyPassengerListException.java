
package com.capg.fms.passenger.exceptions;

/****************************************************************************************************************************
- File Name        : EmptyPassengerListException.java 
- Author           : Capgemini 
- Creation Date    : 12/08/2020
****************************************************************************************************************************/

public class EmptyPassengerListException extends RuntimeException {
	
	public EmptyPassengerListException(String message) {
		super(message);
	}
}
