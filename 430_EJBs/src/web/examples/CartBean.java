package web.examples;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

@Stateful
public class CartBean {

    private double total = 0.0;
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
        total += item.getCost();
    }

    public void removeItem(Item item) {
        items.remove(item);
        total -= item.getCost();
    }

    public double checkOut() {
        return total;
    }
}
