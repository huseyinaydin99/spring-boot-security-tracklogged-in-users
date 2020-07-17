package com.huseyinaydin.security;

import com.huseyinaydin.controller.UserController;
import com.huseyinaydin.redirect.CustomRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("myLogoutSuccessHandler")
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private CustomRedirectStrategy redirectStrategy;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        System.out.println("onLogoutSuccess");
        if (session != null){
            session.removeAttribute("user");
            System.out.println("onLogoutSuccess if");
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/");
        }
    }
}
