/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import com.vending.exceptions.InvalidCoinException;

/**
 *
 * @author Vignaraj
 */

public enum Coin {
	TenPence(BigDecimal.valueOf(0.10), Locale.UK), TwentyPence(BigDecimal
			.valueOf(0.20), Locale.UK), FiftyPence(BigDecimal.valueOf(0.50),
			Locale.UK), OnePound(BigDecimal.valueOf(1.00), Locale.UK);

	Coin(BigDecimal value, Locale locale) {
		this.value = value;
		this.value = this.value.setScale(2, RoundingMode.HALF_EVEN);
		this.locale = locale;
	}

	private BigDecimal value;
	private Locale locale;
	private static final Logger LOGGER = Logger.getLogger(Coin.class.getName());

	public BigDecimal getValue() {
		return value;
	}

	public Locale getLocale() {
		return locale;
	}

	private String getCoinInLocalFormat() {
		NumberFormat gbpCostFormat = getCurrentCostFormat();
		return gbpCostFormat.format(value.doubleValue());
	}

	private static NumberFormat getCurrentCostFormat() {
		NumberFormat gbpCostFormat = NumberFormat
				.getCurrencyInstance(Locale.UK);
		gbpCostFormat.setMinimumFractionDigits(2);
		gbpCostFormat.setMaximumFractionDigits(2);
		return gbpCostFormat;
	}

	@Override
	public String toString() {
		return getCoinInLocalFormat();
	}

	public static Coin getEnum(String value) throws InvalidCoinException {
		BigDecimal big = new BigDecimal(value);
		big.setScale(2, RoundingMode.HALF_EVEN);

		for (Coin coin : values()) {
			if (coin.getValue().compareTo(big) == 0) {
				return coin;
			}
		}
		throw new InvalidCoinException(value);
	}

	public static List<Coin> calculateChange(String value)
			throws InvalidCoinException {
		List<Coin> coins = new ArrayList<>();
		BigDecimal change = new BigDecimal(value);
		BigDecimal zeroChange = new BigDecimal("0.00");

		while (change.compareTo(zeroChange) > 0) {

			if (change.subtract(Coin.OnePound.getValue()).compareTo(zeroChange) >= 0) {
				coins.add(OnePound);
				change = change.subtract(OnePound.getValue());
			} else if (change.subtract(Coin.FiftyPence.getValue()).compareTo(
					zeroChange) >= 0) {
				coins.add(FiftyPence);
				change = change.subtract(FiftyPence.getValue());
			} else if (change.subtract(Coin.TwentyPence.getValue()).compareTo(
					zeroChange) >= 0) {
				coins.add(TwentyPence);
				change = change.subtract(TwentyPence.getValue());
			} else if (change.subtract(Coin.TenPence.getValue()).compareTo(
					zeroChange) >= 0) {
				coins.add(TenPence);
				change = change.subtract(TenPence.getValue());
			} else {
				change = change.subtract(change);
				LOGGER.severe("Unable to give change for " + value
						+ "; kindly accept : " + coins);
			}
		}
		return coins;
	}
}