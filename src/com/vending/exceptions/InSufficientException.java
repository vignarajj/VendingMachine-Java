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
public class InSufficientException extends RuntimeException {

    private String message;

    public InSufficientException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
