/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending;

import com.vending.exceptions.InvalidCoinException;
import com.vending.inventory.Coins;
import com.vending.inventory.Products;
import com.vending.model.Coin;
import com.vending.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vignaraj
 */
public class VendingMachine implements Machine {

    private Product product = null;
    private Products products;
    private Coins coins = null;
    private List<Coin> coinsList;

    public VendingMachine() {
        super();
        this.products = Products.getSingeltonInstance();
        this.coins = Coins.getSingeltonInstance();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        initializeFunction();
    }

    public static void initializeFunction() {
        VendingMachine machine = new VendingMachine();
        machine.getMachineStatus();
        machine.getNumberofProducts();
        machine.getNumberofCoins();
        machine.getCoins();
        machine.getProducts();
    }

    /**
     * @return coins. Eject the coins.
     */
    @Override
    public List<Coin> ejectCoins() {
        return this.getCoins();
    }

    /**
     * @return false, To off the vending machine.
     */
    @Override
    public boolean switchOffMachine() {
        System.out.println("Now Machine is Off");
        return Utils.machineState = false;
    }

    /**
     * @return true, To on the vending machine.
     */
    @Override
    public boolean switchOnMachine() {
        System.out.println("Now Machine is Off");
        return Utils.machineState = true;
    }

    /**
     * @return true or false, To get the state of vending machine.
     */
    @Override
    public boolean getMachineStatus() {
        System.out.println("Status of Vending Machine is " + Utils.machineState);
        return Utils.machineState;
    }

    /**
     * @return integer, Get the number for products available in machine.
     */
    @Override
    public int getNumberofProducts() {
        System.out.println("Number of Products Left : " + this.products.getCurrentProducts().size());
        return this.products.getCurrentProducts().size();
    }

    /**
     * @return integer, Get the number for coins available in machine.
     */
    @Override
    public int getNumberofCoins() {
        System.out.println("Number of Coins Left : " + this.coins.getAvailableChange().size());
        return this.coins.getAvailableChange().size();
    }

    /**
     * @return coins list, Get the coins available in machine.
     */
    @Override
    public List<Coin> getCoins() {
        System.out.println("Available Coins : " + this.coins.getAvailableChange());
        return this.coins.getAvailableChange();
    }

    /**
     * @return void, set the coins.
     */
    @Override
    public void setCoins(List<Coin> coins) {
        this.coinsList = coins;
    }

    /**
     * @return product list, Get the product available in machine.
     */
    @Override
    public List<Product> getProducts() {
        System.out.println("Available Products : " + this.products.getCurrentProducts());
        return this.products.getCurrentProducts();
    }

    /**
     * @return product, Get the product.
     */
    @Override
    public Product getProduct() {
        return product;
    }

    /**
     * @return void, Set the product.
     */
    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return null, cancel the product.
     */
    @Override
    public Product cancelProduct() {
        Products.getSingeltonInstance()
                .removeProduct(this.getProduct());
        this.setProduct(null);
        return null;
    }
    /**
     * @return true or false, Check the inserted coins are sufficient for selected product.
     */
    public boolean hasSufficientCoins() throws InvalidCoinException {
        Product selectedProduct = this.getProduct();
        List<Coin> enteredCoins = this.getCoins();
        BigDecimal sumOfCoins = Utils.sum(enteredCoins);
        boolean willDispatch = false;

        System.out.println("Please enter " + selectedProduct.getPrice()
                + " to purchase your selection "
                + selectedProduct.getDescription());

        if (enteredCoins == null) {
            enteredCoins = new ArrayList<>();
        }
        switch (sumOfCoins.compareTo(selectedProduct.getPrice())) {
            case -1:
                System.out.println("More coins needed to purchase the product; please put remaning "
                        + ((sumOfCoins.subtract(selectedProduct.getPrice()))
                                .toString().substring(1)) + " amount");
                break;
            case 0:
                willDispatch = true;
                cancelProduct();
                break;
            default:
                willDispatch = true;
                updateInventoryAndReturnChange();
                cancelProduct();
                ejectCoins();
                break;
        }
        return willDispatch;
    }
    /**
     * @return void, update the coins and returns the change.
     */
    private void updateInventoryAndReturnChange() throws InvalidCoinException {
        Coins.getSingeltonInstance()
                .addCoinCollection(this.getCoins());
        BigDecimal change = Utils.sum(this.getCoins()).subtract(
                this.getProduct().getPrice());
        List<Coin> changeList = Coin.calculateChange(change.toString());
        this.setCoins(changeList);
        Coins.getSingeltonInstance().removeCoinCollection(changeList);
    }
}
