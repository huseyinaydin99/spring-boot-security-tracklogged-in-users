package com.huseyinaydin.security;

import com.huseyinaydin.ActiveUserStore;
import com.huseyinaydin.controller.UserController;
import com.huseyinaydin.redirect.CustomRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("myAuthenticationSuccessHandler")
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    ActiveUserStore activeUserStore;

    @Autowired
    private CustomRedirectStrategy redirectStrategy;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("onAuthenticationSuccess");
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            LoggedUser user = new LoggedUser(authentication.getName(), activeUserStore);
            session.setAttribute("user", user);
            System.out.println("onAuthenticationSuccess if");
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/");
        }
    }
}
