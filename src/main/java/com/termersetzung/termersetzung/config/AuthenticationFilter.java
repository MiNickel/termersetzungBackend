package com.termersetzung.termersetzung.config;

import io.jsonwebtoken.Jwts;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.termersetzung.termersetzung.config.SecurityConstants.TOKEN_PREFIX;
import static com.termersetzung.termersetzung.config.SecurityConstants.*;

@Component("authenticationFilter")
public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String requestPath = httpRequest.getServletPath();

            String type = checkRequest(requestPath);

            if (type.equals("prof")) {
                String header = httpRequest.getHeader("Authorization");
                Jwts.parser().setSigningKey(SECRET_PROF).parseClaimsJws(header.replace(TOKEN_PREFIX, "")).getBody();
            } else if (type.equals("student")) {
                String header = httpRequest.getHeader("Authorization");
                Jwts.parser().setSigningKey(SECRET_STUDENT).parseClaimsJws(header.replace(TOKEN_PREFIX, "")).getBody();
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage(), ex);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String checkRequest(String requestPath) {
        if (requestPath.matches("/exam/examiner/.+") || requestPath.matches("/exam/.+")
                || requestPath.equals("/student/?studentIds=.+") || requestPath.matches("/student/.+")
                || requestPath.equals("/studentexam/all") || requestPath.matches("/studentexam/all/.+")
                || (requestPath.matches("/studentexam/.+") && !requestPath.matches("/studentexam/.+/.+/.+/.+"))
                || requestPath.equals("/exam") || requestPath.matches("/exercise/examiner/.+")
                || requestPath.equals("/exercise")
                || (requestPath.matches("/exercise/.+") && !requestPath.equals("/exercise/student")
                        && !requestPath.equals("/exercise/check") && !requestPath.matches("/exercise/student/.+"))) {
            return "prof";
        } else if (requestPath.equals("/exercise/student") || requestPath.equals("/exercise/check")
                || requestPath.matches("/studentexam/.+/.+/.+/.+") || requestPath.matches("/studentexercise/.+")
                || requestPath.matches("/studentexercise/.+/.+") || requestPath.equals("/studentexercise/")
                || requestPath.matches("/exercise/student/.+") || requestPath.equals("/studentexam/")) {
            return "student";
        }
        // exercise/id ?
        return "none";
    }
}
