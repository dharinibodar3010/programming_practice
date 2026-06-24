package com.example.config;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        String log =
                LocalDateTime.now()
                + " | "
                + req.getMethod()
                + " | "
                + req.getRequestURI()
                + "\n";

        FileWriter writer =
                new FileWriter("access.log", true);

        writer.write(log);
        writer.close();

        chain.doFilter(request, response);
    }
}
