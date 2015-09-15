package com.svargheese.entities;

/**
 * Represents a basket item.
 * 
 * @author Sai Vargheese
 *
 */
public enum Item {

	BANANA("Banana"), ORANGE("Orange"), APPLE("Apple"), LEMON("Lemon"), PEACH(
			"Peach");

	public final String displayName;

	private Item(String displayName) {
		this.displayName = displayName;
	}
}
