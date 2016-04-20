package org.bubba.iec;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Iterator;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
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
	public void testGetOrderTotal_0_tax()
	{
		OrderItem[] orderItems = new OrderItem[3];

		Item item1 = new Item(1, "Some Name 1", new BigDecimal("1.23"), Type.MATERIAL);
		orderItems[0] = new OrderItem(item1, 3);

		Item item2 = new Item(2, "aSome Name 2", new BigDecimal("21.23"), Type.SERVICE);
		orderItems[1] = new OrderItem(item2, 6);

		Item item3 = new Item(1, "some Name 3", new BigDecimal("1.17"), Type.MATERIAL);
		orderItems[2] = new OrderItem(item3, 23);

		Order order = new Order(orderItems);

		BigDecimal result = order.getOrderTotal(new BigDecimal("0.0"));
		assertTrue("order total should be 157.98 but was " + result.toString(), new BigDecimal("157.98").setScale(2, RoundingMode.UP).equals(result));
	}

	@Test
	public void testGetItems()
	{
		OrderItem[] orderItems = new OrderItem[4];

		Item item1 = new Item(1, "Some Name 1", new BigDecimal("1.23"), Type.MATERIAL);
		orderItems[0] = new OrderItem(item1, 3);

		Item item2 = new Item(2, "aSome Name 2", new BigDecimal("21.23"), Type.SERVICE);
		orderItems[1] = new OrderItem(item2, 6);

		Item item3 = new Item(1, "some Name 3", new BigDecimal("1.17"), Type.MATERIAL);
		orderItems[2] = new OrderItem(item3, 23);

		Item item4 = new Item(4, "SoMe NaMe 3", new BigDecimal("61.17"), Type.MATERIAL);
		orderItems[3] = new OrderItem(item4, 23);

		Order order = new Order(orderItems);

		Collection<Item> items = order.getItems();
		Assert.assertNotNull("items should not be null", items);
		assertEquals(4, items.size());
		Iterator<Item> iterator = items.iterator();
		assertEquals("aSome Name 2", iterator.next().getName());
		assertEquals("Some Name 1", iterator.next().getName());
		assertEquals("some Name 3", iterator.next().getName());
		assertEquals("SoMe NaMe 3", iterator.next().getName());
	}
	
	@Test
	public void testGetItems_lambda()
	{
		OrderItem[] orderItems = new OrderItem[4];

		Item item1 = new Item(1, "Some Name 1", new BigDecimal("1.23"), Type.MATERIAL);
		orderItems[0] = new OrderItem(item1, 3);

		Item item2 = new Item(2, "aSome Name 2", new BigDecimal("21.23"), Type.SERVICE);
		orderItems[1] = new OrderItem(item2, 6);

		Item item3 = new Item(1, "some Name 3", new BigDecimal("1.17"), Type.MATERIAL);
		orderItems[2] = new OrderItem(item3, 23);

		Item item4 = new Item(4, "SoMe NaMe 3", new BigDecimal("61.17"), Type.MATERIAL);
		orderItems[3] = new OrderItem(item4, 23);

		Order order = new Order(orderItems);

		Collection<Item> items = order.getItemsLambda();
		Assert.assertNotNull("items should not be null", items);
		assertEquals(4, items.size());
		Iterator<Item> iterator = items.iterator();
		assertEquals("aSome Name 2", iterator.next().getName());
		assertEquals("Some Name 1", iterator.next().getName());
		assertEquals("some Name 3", iterator.next().getName());
		assertEquals("SoMe NaMe 3", iterator.next().getName());
	}
	
	@Test
	public void testSerialize()
	{
		OrderItem[] orderItems = new OrderItem[4];

		Item item1 = new Item(1, "Some Name 1", new BigDecimal("1.23"), Type.MATERIAL);
		orderItems[0] = new OrderItem(item1, 3);

		Item item2 = new Item(2, "aSome Name 2", new BigDecimal("21.23"), Type.SERVICE);
		orderItems[1] = new OrderItem(item2, 6);

		Item item3 = new Item(1, "some Name 3", new BigDecimal("1.17"), Type.MATERIAL);
		orderItems[2] = new OrderItem(item3, 23);

		Item item4 = new Item(4, "SoMe NaMe 3", new BigDecimal("61.17"), Type.MATERIAL);
		orderItems[3] = new OrderItem(item4, 23);

		Order order = new Order(orderItems);

		byte[] serializedOrder = SerializationUtils.serialize(order);
		assertTrue(serializedOrder.length > 99);
		
		try{			// just to make sure
			MySerializableClass m = new MySerializableClass();
			serializedOrder = SerializationUtils.serialize(m);
		}catch(SerializationException e){
			// success
			return;
		}
		Assert.fail("should have thrown SerializationException and not made it here");
	}
}

class NonSerializableClass{
	private int x = 23;
}
class MySerializableClass implements Serializable{
	private int xx = 23;
	private NonSerializableClass nsc = new NonSerializableClass();
}