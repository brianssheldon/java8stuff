package org.bubba.iec;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class Order implements Serializable
{
	private final OrderItem[] orderItems;

	public Order(OrderItem[] orderItems)
	{
		this.orderItems = orderItems;
	}

// Returns the total order cost after the tax has been applied
	public float getOrderTotal(BigDecimal taxRate)
	{
		return 0; // implement this method
	}

	/**
	 * Returns a Collection of all items sorted by item name
	 *
	 * (case-insensitive).
	 *
	 * @return Collection
	 */
	public Collection getItems()
	{
		return null; // implement this method
	}
}