package com.epam.training.fooddelivery.handlers;

import com.epam.training.fooddelivery.config.SecurityConfig;
import com.epam.training.fooddelivery.domain.Customer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(200);
        request.authenticate(response);
        SecurityConfig.AUTHENTICATED_CUSTOMER = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
