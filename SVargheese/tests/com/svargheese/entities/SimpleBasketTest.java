package com.svargheese.entities;

import org.junit.Assert;
import org.junit.Test;

public class SimpleBasketTest {

	@Test
	public void testBasket() {
		Assert.assertNotNull(new SimpleBasket());
	}

	@Test
	public void testAddItem() {
		Basket b = new SimpleBasket();

		// Quantity Validation
		Assert.assertTrue(b.addItem(Item.APPLE, "1.11", "z9").isFailure);
		Assert.assertTrue(b.addItem(Item.APPLE, "1.11", ".9999").isFailure);
		Assert.assertTrue(b.addItem(Item.APPLE, "1.11", "-1").isFailure);
		Assert.assertTrue(b.addItem(Item.APPLE, "1.11", null).isFailure);

		// Cost Validation
		Assert.assertTrue(b.addItem(Item.APPLE, "1.11z", "9").isFailure);
		Assert.assertFalse(b.addItem(Item.APPLE, "1", "9").isFailure);
		Assert.assertFalse(b.addItem(Item.APPLE, "1.0", "9").isFailure);
		Assert.assertFalse(b.addItem(Item.APPLE, "1.11111", "9").isFailure);

		/* Scale Validation */
		b = new SimpleBasket();
		b.addItem(Item.APPLE, "0.111112345", "10");
		Assert.assertTrue(b.getGrandTotal() == 1.1);

		b = new SimpleBasket();
		b.addItem(Item.ORANGE, "1.115111", "10");
		Assert.assertTrue(b.getGrandTotal() == 11.2);

		b = new SimpleBasket();
		b.addItem(Item.ORANGE, ".104", "10");
		Assert.assertTrue(b.getGrandTotal() == 1);

		b = new SimpleBasket();
		b.addItem(Item.ORANGE, ".145", "10");
		Assert.assertTrue(b.getGrandTotal() == 1.5);

		b = new SimpleBasket();
		b.addItem(Item.ORANGE, ".146", "10");
		Assert.assertTrue(b.getGrandTotal() == 1.5);

		b = new SimpleBasket();
		b.addItem(Item.ORANGE, ".144", "10");
		Assert.assertTrue(b.getGrandTotal() == 1.4);

	}

	@Test
	public void testGetPrintableLines() {

		SimpleBasket b = new SimpleBasket();
		b.addItem(Item.ORANGE, ".144", "10");
		Assert.assertNotNull(b.getPrintableLines());
		Assert.assertTrue(b.getPrintableLines().size() > 0);
	}

	@Test
	public void testToConsole() {
		SimpleBasket b = new SimpleBasket();
		b.addItem(Item.ORANGE, ".144", "10");
		Assert.assertNotNull(b.toConsole());
	}

}
