package org.bubba.iec;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable
{
	private Integer key;
	private String name;
	private BigDecimal price;
	private Type type;

	public Item(Integer key, String name, BigDecimal price, Type type)
	{
		this.key = key;
		this.name = name;
		this.price = price;
		this.type = type;
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

	public Type getType()
	{
		return type;
	}
}
