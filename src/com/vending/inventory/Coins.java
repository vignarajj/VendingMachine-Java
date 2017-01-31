/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.vending.model.Coin;

/**
 *
 * @author Vignaraj
 */

public class Coins {

	private Coins() {
	}

	private static final Coins singletonInstance = new Coins();
	private static List<Coin> coins = new ArrayList<Coin>();
	private static final Logger LOGGER = Logger
			.getLogger(Coins.class.getName());

	public static Coins getSingeltonInstance() {
		return singletonInstance;
	}

	static {
		coins.add(Coin.FiftyPence);
		coins.add(Coin.FiftyPence);
		coins.add(Coin.FiftyPence);
		coins.add(Coin.TenPence);
		coins.add(Coin.TenPence);
		coins.add(Coin.TenPence);
		coins.add(Coin.TenPence);
		coins.add(Coin.OnePound);
		coins.add(Coin.OnePound);
		coins.add(Coin.OnePound);
		coins.add(Coin.TwentyPence);
		coins.add(Coin.TwentyPence);
		coins.add(Coin.TwentyPence);
	}

	public void removeCoin(Coin coin) {
		coins.remove(coin);
	}

	public List<Coin> getAvailableChange() {
		return coins;
	}

	public void addChange(Coin change) {
		coins.add(change);
	}

	public void addCoinCollection(List<Coin> morecoins) {
		coins.addAll(morecoins);
	}

	public void removeCoinCollection(List<Coin> removecoins) {
		for (Coin coin : removecoins) {
			boolean wasAbleToRemove = coins.remove(coin);
			LOGGER.info(coin.toString());
		}
	}
}