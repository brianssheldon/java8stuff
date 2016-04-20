package org.bubba.iec;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Order implements Serializable
{
	private final OrderItem[] orderItems;

	public Order(OrderItem[] orderItems)
	{
		this.orderItems = orderItems;
	}

// Returns the total order cost after the tax has been applied
	public BigDecimal getOrderTotal(BigDecimal taxRate)
	{

		BigDecimal total = BigDecimal.ZERO.setScale(2, RoundingMode.UP);

		for(OrderItem orderItem : orderItems)
		{
			total = total.add(
				orderItem.getItem().getPrice().multiply(new BigDecimal(orderItem.getQuantity())).setScale(2, RoundingMode.UP));
		}
		return total; // implement this method
	}

	/**
	 * Returns a Collection of all items sorted by item name
	 * (case-insensitive).
	 * @return Collection
	 */
	public Collection<Item> getItems()
	{

		List<Item> items = new ArrayList<Item>();
		for(OrderItem orderItem : orderItems)
		{
			items.add(orderItem.getItem());
		}
		
		Collections.sort(items, new Comparator<Item>()
		{
			public int compare(Item o1, Item o2)
			{
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		return items; // implement this method
	}
}
