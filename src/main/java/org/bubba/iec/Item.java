package org.bubba.iec;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable
{
	private Integer key;
	private String name;
	private BigDecimal price;

	public Item(Integer key, String name, BigDecimal price)
	{
		this.key = key;
		this.name = name;
		this.price = price;
	}

	public Integer getKey()
	{
		return key;
	}

	public String getName()
	{
		return name;
	}

	public BigDecimal getPrice()
	{
		return price;
	}
}
