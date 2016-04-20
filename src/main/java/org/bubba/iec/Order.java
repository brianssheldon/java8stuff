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
	private long number;
	private final OrderItem[] orderItems;

	public Order(OrderItem[] orderItems)
	{
		this.orderItems = orderItems;
	}

// Returns the total order cost after the tax has been applied
	public BigDecimal getOrderTotal(BigDecimal taxRate)
	{
		BigDecimal total = BigDecimal.ZERO.setScale(2, RoundingMode.UP);
		Item item;
		BigDecimal tempAmount;
		
		for(OrderItem orderItem : orderItems)
		{
			item = orderItem.getItem();
			
			if(item.getType().equals(Type.MATERIAL))
			{
				tempAmount = item.getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
				total = total.add(tempAmount);
				
				tempAmount = tempAmount.multiply(taxRate).setScale(2, RoundingMode.UP);
				
				total = total.add(tempAmount);
			}
			else
			{
				total = total.add(item.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
			}
		}
		return total.setScale(2, RoundingMode.UP); // implement this method
	}

	/**
	 * Returns a Collection of all items sorted by item name
	 * (case-insensitive).
	 * @return Collection
	 */
	public Collection<Item> getItems()
	{
		List<Item> items = new ArrayList<>();
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
	
	/**
	 * Returns a Collection of all items sorted by item name
	 * (case-insensitive).
	 * @return Collection
	 */
	public Collection<Item> getItemsLambda()
	{
		List<Item> items = new ArrayList<>();
		for(OrderItem orderItem : orderItems)
		{
			items.add(orderItem.getItem());
		}
		
		Collections.sort(items, (Item lh, Item rh) -> lh.getName().compareToIgnoreCase(rh.getName()));

		return items; // implement this method
	}

	public long getNumber()
	{
		return number;
	}

	public void setNumber(long number)
	{
		this.number = number;
	}
}
