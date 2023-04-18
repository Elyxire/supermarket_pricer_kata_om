package com.kata.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the description of a Basket is made of list of items that were bought in the supermarket
 * 
 * @author Othman
 *
 */

public class Basket {
	private List<Item> items = new ArrayList<>();

	public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
