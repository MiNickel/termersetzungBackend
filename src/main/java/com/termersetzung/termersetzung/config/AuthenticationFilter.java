package com.termersetzung.termersetzung.config;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.termersetzung.termersetzung.config.SecurityConstants.TOKEN_PREFIX;
import static com.termersetzung.termersetzung.config.SecurityConstants.SECRET;

@Component("authenticationFilter")
public class AuthenticationFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String requestPath = httpRequest.getServletPath();

            // URL für Endpoints die nur für Prüfende zugänglich sind
            if (requestPath.equals("/prof")) {
                String header = httpRequest.getHeader("Authorization");
                Jwts.parser().setSigningKey(SECRET).parseClaimsJws(header.replace(TOKEN_PREFIX, "")).getBody();
            }
        } catch (Exception ex) {

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
