package org.bubba.iec;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Iterator;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class OrderTest 
{

    @Test
    public void testGetOrderTotal()
    {
		OrderItem[] orderItems = new OrderItem[3];
		
		Item item1 = new Item(1, "Some Name 1", new BigDecimal("1.23"), Type.MATERIAL);
		orderItems[0] = new OrderItem(item1, 3);
		
		Item item2 = new Item(2, "aSome Name 2", new BigDecimal("21.23"), Type.SERVICE);
		orderItems[1] = new OrderItem(item2, 6);
		
		Item item3 = new Item(1, "some Name 3", new BigDecimal("1.17"), Type.MATERIAL);
		orderItems[2] = new OrderItem(item3, 23);
		
        Order order = new Order(orderItems);
		
		BigDecimal result = order.getOrderTotal(new BigDecimal("0.076"));
		assertTrue("order total should be 160.32 but was " + result.toString(), new BigDecimal("160.32").setScale(2, RoundingMode.UP).equals(result));
    }
	
    @Test
    public void testGetOrderTotal_no_orderItems()
    {
		Order order = new Order(new OrderItem[0]);
		
		BigDecimal result = order.getOrderTotal(new BigDecimal("0.076"));
		assertTrue("order total should be 0.00 but was " + result.toString(), new BigDecimal("0.00").setScale(2, RoundingMode.UP).equals(result));
    }
	
	@Test
	public void testGetItems()
	{
		OrderItem[] orderItems = new OrderItem[3];
		
		Item item1 = new Item(1, "Some Name 1", new BigDecimal("1.23"), Type.MATERIAL);
		orderItems[0] = new OrderItem(item1, 3);
		
		Item item2 = new Item(2, "aSome Name 2", new BigDecimal("21.23"), Type.SERVICE);
		orderItems[1] = new OrderItem(item2, 6);
		
		Item item3 = new Item(1, "some Name 3", new BigDecimal("1.17"), Type.MATERIAL);
		orderItems[2] = new OrderItem(item3, 23);
		
        Order order = new Order(orderItems);
		
		Collection<Item> items = order.getItems();
		Assert.assertNotNull("items should not be null", items);
		assertEquals(3, items.size());
		Iterator<Item> iterator = items.iterator();
		assertEquals("aSome Name 2", iterator.next().getName());
		assertEquals("Some Name 1", iterator.next().getName());
		assertEquals("some Name 3", iterator.next().getName());
	}
}

/* ***********************************************************
 *                                                           *
 * Copyright (C) 2015 The Hertz Corporation                  *
 * All Rights Reserved. (Unpublished.)                       *
 * The information contained herein is confidential and      *
 * proprietary to The Hertz Corporation and may not be       *
 * duplicated, disclosed to third parties, or used for any   *
 * purpose not expressly authorized by it.  Any unauthorized *
 * use, duplication or disclosure is prohibited by law.      *
 *                                                           *
 ************************************************************/