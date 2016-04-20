package org.bubba.iec;

import java.io.Serializable;

public class OrderItem implements Serializable
{
	private Item item;
	private int quantity;

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
