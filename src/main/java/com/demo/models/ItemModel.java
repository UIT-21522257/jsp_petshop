package com.demo.models;
import java.util.List;

import com.demo.entities.Item;
public class ItemModel {
	public static int exist(List<Item> cart, int petId) {
	    for (int i = 0; i < cart.size(); i++) {
	        if (cart.get(i).getPet().getId() == petId) {
	            return i;
	        }
	    }
	    return -1;
	}
	public static double total(List<Item> cart) {
		double i = 0;
		for (Item item : cart) {
			i += item.getPet().getMoney() * item.getQuantity();
		}
		return i;
	}
}
