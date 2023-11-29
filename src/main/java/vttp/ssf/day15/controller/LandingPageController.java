package vttp.ssf.day15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp.ssf.day15.model.Item;

// @Controller
// @RequestMapping(path={"/" , "/index.html"})
public class LandingPageController {

    // @GetMapping
    public String getIndex(Model model, HttpSession sess){
        model.addAttribute("item", new Item());
        model.addAttribute("cart", sess.getAttribute("cart"));
        return "index";
    }
    
}
