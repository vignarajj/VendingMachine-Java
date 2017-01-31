/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.inventory;

import java.util.ArrayList;
import java.util.List;

import com.vending.model.Product;

/**
 *
 * @author Vignaraj
 */

public class Products {

	private Products() {
	}

	private static final Products singeltonInstance = new Products();
	private static List<Product> products = new ArrayList<Product>();

	public static Products getSingeltonInstance() {
		return singeltonInstance;
	}

	static {
		products.add(Product.A);
		products.add(Product.B);
		products.add(Product.C);
		products.add(Product.C);
		products.add(Product.C);
		products.add(Product.B);
		products.add(Product.B);
		products.add(Product.A);
	}

	public void removeProduct(Product p) {
		products.remove(p);
	}

	public List<Product> getCurrentProducts() {
		return products;
	}
        public void addProduct(Product p){
            products.add(p);
        }
}
