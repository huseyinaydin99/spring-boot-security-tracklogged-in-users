package com.huseyinaydin.controller;

import com.huseyinaydin.ActiveUserStore;
import com.huseyinaydin.security.LoggedUser;
import com.huseyinaydin.services.SessionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private ActiveUserStore activeUserStore;

    @Autowired
    @Qualifier("sessionServices")
    private SessionServices sessionServices;

    @GetMapping("/")
    public String getLoggedUsers(Locale locale, Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        LoggedUser user = (LoggedUser)httpSession.getAttribute("user");

        //System.out.println("--");
        model.addAttribute("users", activeUserStore.getUsers());
        if(user!=null)
        model.addAttribute("user",user.getUsername());
        //System.out.println("---");
        /*List<String> users = this.sessionServices.getUsersFromSessionRegistry();
        System.out.println("getLoggedUsers");
        users.stream().forEach(i-> System.out.println(i));
        model.addAttribute("kullanici",this.sessionServices.getUsersFromSessionRegistry());*/
        return "users";
    }

    @GetMapping("/anasayfa")
    public String getLoggedUsers2(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        LoggedUser user = (LoggedUser)httpSession.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        //System.out.println("--");
        modelAndView.addObject("users", activeUserStore.getUsers());
        if(user!=null)
            modelAndView.addObject("user",user.getUsername());
        //System.out.println("---");
        /*List<String> users = this.sessionServices.getUsersFromSessionRegistry();
        System.out.println("getLoggedUsers");
        users.stream().forEach(i-> System.out.println(i));
        model.addAttribute("kullanici",this.sessionServices.getUsersFromSessionRegistry());*/
        return "users";
    }

    public ActiveUserStore getActiveUserStore() {
        return activeUserStore;
    }

    public void setActiveUserStore(ActiveUserStore activeUserStore) {
        this.activeUserStore = activeUserStore;
    }

    public SessionServices getSessionServices() {
        return sessionServices;
    }

    public void setSessionServices(SessionServices sessionServices) {
        this.sessionServices = sessionServices;
    }
}
