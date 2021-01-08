package com.laioffer.onlineShop.controller;

import com.laioffer.onlineShop.entity.Authorities;
import com.laioffer.onlineShop.entity.Cart;
import com.laioffer.onlineShop.entity.Customer;
import com.laioffer.onlineShop.entity.User;
import com.laioffer.onlineShop.service.AuthoritiesService;
import com.laioffer.onlineShop.service.CustomerService;
import com.laioffer.onlineShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginUIController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserService userService;

    @Autowired
    AuthoritiesService authoritiesService;

    @GetMapping("/login")
    public String loginPage(@ModelAttribute User user, HttpServletRequest request) {
        if(request.getSession().getAttribute("user") != null) {
           return "redirect:/cart";
        }
        return "loginWelcome";
    }

    @GetMapping("/requestAdmin")
    public String loginPage(HttpServletRequest request) {
        String emailId = request.getSession().getAttribute("user").toString();
        authoritiesService.updateAnAuthorities(authoritiesService.getAuthoritiesByEmailId(emailId),
                "ADMIN");
        return "redirect:/cart";
    }

    @PostMapping("/login")
    public String checkUserInfo(@ModelAttribute User user, HttpServletRequest request) {
        if(userService.isUserCorrect(user)) {
            request.getSession().setAttribute("user", user.getEmailId());
            return "redirect:/cart";
        }
        return "redirect:/createAccount";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/login";
    }

    @GetMapping("/createAccount")
    public String createdAccount(@ModelAttribute Customer customer) {
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String SaveAccount(@ModelAttribute Customer customer) {
        customer.setCart(new Cart());
        customerService.save(customer);
        Authorities authorities = new Authorities();
        authorities.setEmailId(customer.getUser().getEmailId());
        authorities.setAuthorities("USER");
        authoritiesService.saveAnAuthorities(authorities);
        return "redirect:/login";
    }
}
