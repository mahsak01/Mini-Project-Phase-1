package com.example.uni.Security;

import com.example.uni.Dto.CredentialsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private static final ObjectMapper MAPPER = new ObjectMapper();


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if ("/api/v1/signIn".equals(request.getServletPath()) &&
                HttpMethod.POST.matches(request.getMethod())){
            CredentialsDto credential = MAPPER.readValue(request.getInputStream(), CredentialsDto.class);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            credential.getLogin(),
                            credential.getPassword()
                    )
            );
        }

        filterChain.doFilter(request,response);
    }
}
