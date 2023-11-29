package vttp.ssf.day15;

import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import vttp.ssf.day15.model.Item;

public class Utils {

    private static final String ATTR_CART = "cart";
    private static final String ATTR_NAME = "name";
    
    public static List<Item> getCart(HttpSession sess) {
        List<Item> cart = (List<Item>) sess.getAttribute((ATTR_CART));

        if (null==cart) {
            cart = new LinkedList<Item>();
            sess.setAttribute(ATTR_CART, cart);
        }

        return cart;
    }

    public static String getName(HttpSession sess) {
        String name = (String) sess.getAttribute((ATTR_NAME));

        // if (null==cart) {
        //     cart = new LinkedList<Item>();
        //     sess.setAttribute(ATTR_CART, cart);
        // }

        return name;
    }
}
