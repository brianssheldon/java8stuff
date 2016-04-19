package org.bubba.iec;

import java.io.Serializable;

public class OrderItem implements Serializable
{    
    private boolean taxable;
    private Type type;
    private Item[] items;

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}