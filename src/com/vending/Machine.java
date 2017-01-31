/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending;

import com.vending.model.Coin;
import com.vending.model.Product;
import java.util.List;

/**
 *
 * @author Vignaraj
 */
public interface Machine {
    public List<Coin> ejectCoins();
    public boolean switchOffMachine();
    public boolean switchOnMachine();
    public boolean getMachineStatus();
    public int getNumberofProducts();
    public int getNumberofCoins();
    public List<Coin> getCoins();
    public void setCoins(List<Coin> coins);
    public List<Product> getProducts();
    
    
    public Product getProduct();
    public void setProduct(Product product);
    public Product cancelProduct();
}
