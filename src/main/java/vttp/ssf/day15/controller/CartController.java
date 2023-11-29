package vttp.ssf.day15.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.ssf.day15.Utils;
import vttp.ssf.day15.model.Item;
import vttp.ssf.day15.repository.CartRepository;

@Controller
@RequestMapping("/cart")
public class CartController {

    public static final String ATTR_ITEM = "item";

    @Autowired
	private CartRepository cartRepo;

    @GetMapping
    public ModelAndView getCart(@RequestParam String name, HttpSession sess, Model model) {
        ModelAndView mav = new ModelAndView("cart");
        List<Item> cart;
        if(cartRepo.hasCart(name)) {
            cart = cartRepo.getCart(name);
            System.out.printf("Retrieving %s's cart: %s\n", name, cart);
        } else {
            System.out.printf("New user %s detected\n", name);
            cart = new LinkedList<Item>();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("item", new Item());
        sess.setAttribute("name", name);
        sess.setAttribute("cart", cart);
        mav.setStatus(HttpStatusCode.valueOf(200));

        return mav;
    }

    @PostMapping(path="/checkout")
    public ModelAndView postCartCheckout(HttpSession sess) {
        ModelAndView mav = new ModelAndView("redirect:/index.html");

        List<Item> cart = Utils.getCart(sess);
        String name = Utils.getName(sess);
        System.out.printf("Checking out %s's cart %s\n", name, cart);
        cartRepo.setCart(cart, name);
        sess.invalidate();

        // mav.addObject("item", new Item());
        // mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }

    @PostMapping
    public ModelAndView postCart(@Valid @ModelAttribute(ATTR_ITEM) Item item, BindingResult bindings, HttpSession sess) {
        System.out.printf("item: %s\n", item);
        System.out.printf("error %b\n", bindings.hasErrors());

        ModelAndView mav = new ModelAndView("cart");

        if (bindings.hasErrors()) {
            mav.setStatus(HttpStatusCode.valueOf(400));
            return mav;
        }

        List<Item> cart = Utils.getCart(sess);
        if (cart.contains(item)) {
            Item existingItem = cart.get(cart.indexOf(item));
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            cart.add(item);
        }
        
        sess.setAttribute("cart",cart);

        // below is to clear the item posted so that the item does not get kept after the post
        mav.addObject("item", new Item());
        mav.addObject("cart", cart);
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }
    
}
