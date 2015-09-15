package com.svargheese.entities;

import com.svargheese.common.Response;

/**
 * Basket representing collection of Item.
 * 
 * @author Sai Vargheese
 *
 */
public interface Basket {

	/**
	 * To add item into the Basket, idempotent. If item exists values will be
	 * overwritten.
	 * 
	 * @param Item
	 *            - item to be added.
	 * @param costStr
	 *            - String representing the cost in double, gets rounded half
	 *            up. Negative value is accepted.
	 * @param qStr
	 *            - String representing the quantity, negative values not
	 *            accepted.
	 * @return Response.isFailure - true if encountered user or system error.
	 *         Response.message gives meaningful information. Response.isFailure
	 *         - false if API executed successfully.
	 */
	public abstract Response addItem(Item i, String costStr, String qStr);

	/**
	 * 
	 * 
	 * @return Returns total quantity at current state.
	 */
	public abstract int getTotalQty();

	/**
	 * @return Returns total value sum of every item's (quantity x cost) in the
	 *         basket;
	 */
	public abstract double getGrandTotal();

}