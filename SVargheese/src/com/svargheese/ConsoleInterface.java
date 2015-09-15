package com.svargheese;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.svargheese.common.Response;
import com.svargheese.entities.SimpleBasket;
import com.svargheese.entities.Item;

/**
 * Class for getting inputs from user and processing basket.
 * 
 * @author Sai Vargheese.
 *
 */
public class ConsoleInterface {

	public static void main(String[] args) {

		try {
			SimpleBasket b = new SimpleBasket();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			for (Item i : Item.values()) {
				System.out.print(String.format("Enter cost per %s :",
						i.displayName));
				String costStr = br.readLine();
				System.out.print(String.format("Enter quantity for %s :",
						i.displayName));
				String qtyStr = br.readLine();

				Response r = b.addItem(i, costStr, qtyStr);

				if (r.isFailure) {
					System.out.println("Error: " + r.message + " \n");
				} else {
					System.out
							.println("Item added into basket successfully.\n");
				}
			}
			System.out.println(b.toConsole());
		} catch (IOException e) {
			System.out.println("Exception while processing.");
			e.printStackTrace();
		}
	}
}
