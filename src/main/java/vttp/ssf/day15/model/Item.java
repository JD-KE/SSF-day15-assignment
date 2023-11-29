package vttp.ssf.day15.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Item {
    // typically do not hardcode the message like this; sometimes got multilingual usage
    @NotEmpty(message = "Please enter your item's name")
    private String name;
    @Max(value = 10, message = "You cannot order more than 10 items")
    @Min(value = 1, message = "You must order at least 1 item")
    private Integer quantity;

    public Item(@NotEmpty(message = "Please enter your name") String name,
            @Max(value = 10, message = "You cannot order more than 10 items") @Min(value = 1, message = "You must order at least 1 item") Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public Item() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Item [name=" + name + ", quantity=" + quantity + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equalsIgnoreCase(other.name))
            return false;
        return true;
    }

    

    
}
