package org.bubba.iec;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable
{
	private Integer key;
	private String name;
	private BigDecimal price;
	private boolean taxable;
	private Type type;

	public Item(Integer key, String name, BigDecimal price, boolean taxable, Type type)
	{
		this.key = key;
		this.name = name;
		this.price = price;
		this.taxable = taxable;
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
	
	public boolean isTaxable()
	{
		return taxable;
	}

	public Type getType()
	{
		return type;
	}
}
