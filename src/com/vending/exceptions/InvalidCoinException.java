/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.exceptions;
/**
 *
 * @author Vignaraj
 */
public class InvalidCoinException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCoinException() {
		super();
	}

	public InvalidCoinException(String message) {
		super(message);
	}

}
