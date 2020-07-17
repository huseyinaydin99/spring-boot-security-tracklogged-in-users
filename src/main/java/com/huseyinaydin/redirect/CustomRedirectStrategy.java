package com.huseyinaydin.redirect;

import org.springframework.security.web.DefaultRedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response,
                             String redirectUrl) throws java.io.IOException {
        boolean skip = false;
        if (skip) {
            super.sendRedirect(request, response, redirectUrl);
        } else {
            if (!response.isCommitted()) {
                response.sendRedirect(redirectUrl);
            }

        }
    }
}
