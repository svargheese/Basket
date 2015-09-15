package com.svargheese.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.svargheese.common.ConsolePrintable;
import com.svargheese.common.Response;
import com.svargheese.common.Messages;

/**
 * This class represents basket, which can contains of Item. Every Item is
 * associated with cost and quantity.
 * 
 * @author Sai Vargheese
 *
 */
public class SimpleBasket extends ConsolePrintable implements Basket {

	private final Map<Item, Integer> qty = new EnumMap<Item, Integer>(
			Item.class);
	private final Map<Item, BigDecimal> cost = new EnumMap<Item, BigDecimal>(
			Item.class);

	private int totalQty = 0;
	private BigDecimal grandTotal = new BigDecimal("0").setScale(2,
			RoundingMode.HALF_UP);

	public SimpleBasket() {

		for (Item i : Item.values()) {
			qty.put(i, 0);
			cost.put(i, new BigDecimal("0.00"));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.svargheese.entities.Basket#addItem(com.svargheese.entities.Item,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Response addItem(Item i, final String costStr, final String qStr) {

		try {
			BigDecimal costBd = new BigDecimal(costStr).setScale(2,
					RoundingMode.HALF_UP);
			int q = Integer.valueOf(qStr);
			BigDecimal value = costBd.multiply(new BigDecimal(q).setScale(0));

			if (q < 0) {
				return new Response(Messages.NEG_QTY_ERR, true);
			}

			synchronized (this) {
				grandTotal = grandTotal.add(value);
				totalQty = totalQty + q;
				qty.put(i, q);
				cost.put(i, costBd);
			}
			return new Response(Messages.SUCCESS, false);
		} catch (Exception e) {

			if (e instanceof NumberFormatException
					|| e instanceof NullPointerException) {
				return new Response(Messages.INVALID_INPUT, true);

			} else if (e instanceof ArithmeticException) {

				return new Response(Messages.MAX_QTY_ERR, true);
			} else {
				return new Response(Messages.SYSTEM_ERR, true);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.svargheese.entities.Basket#getTotalQty()
	 */
	@Override
	public int getTotalQty() {
		return totalQty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.svargheese.entities.Basket#getGrandTotal()
	 */
	@Override
	public double getGrandTotal() {
		return grandTotal.doubleValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.svargheese.common.ConsolePrintable#getPrintableLines()
	 */

	protected List<String[]> getPrintableLines() {

		List<String[]> lines = new ArrayList<String[]>(Item.values().length + 2);
		String[] headers = { " Item Name ", " Quantity ", " Cost ", " Total " };
		lines.add(headers);

		for (Item i : Item.values()) {

			BigDecimal c = cost.get(i);
			int q = qty.get(i);

			lines.add(new String[] {
					i.displayName,
					String.valueOf(q),
					c.toString(),
					c.multiply(
							new BigDecimal(q).setScale(0,
									RoundingMode.UNNECESSARY)).toString() });
		}

		lines.add(new String[] { " Grand Total ", String.valueOf(totalQty),
				" NA ", grandTotal.toString() });

		return lines;
	}
}
