package org.bubba.iec;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class Order implements Serializable{

    private final OrderItem[] orderItems;

    public Order(OrderItem[] orderItems) {
        this.orderItems = orderItems;
    }

// Returns the total order cost after the tax has been applied
    public BigDecimal getOrderTotal(BigDecimal taxRate) {

        BigDecimal total = BigDecimal.ZERO.setScale(2, RoundingMode.UP);

        for (OrderItem orderItem : orderItems) {
                total = total.add(
                        orderItem.getItem().getPrice().multiply(new BigDecimal(orderItem.getQuantity())).setScale(2, RoundingMode.UP));
        }
        return total; // implement this method
    }

    /**
     * Returns a Collection of all items sorted by item name
     *
     * (case-insensitive).
     *
     * @return Collection
     */
    public Collection getItems() {
        return null; // implement this method
    }
}
