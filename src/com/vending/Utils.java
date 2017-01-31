/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending;

import com.vending.model.Coin;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Vignaraj
 */
public class Utils {
    
    public static boolean machineState = true;

    public static BigDecimal sum(List<Coin> coins) {
        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (Coin c : coins) {
            sum = sum.add(c.getValue());
        }
        return sum;
    }
}
