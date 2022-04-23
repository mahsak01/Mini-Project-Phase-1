package com.example.uni.Security;

import com.example.uni.Dto.CredentialsDto;
import com.example.uni.Dto.UserDto;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collections;

public class UserAuthProvider implements AuthenticationProvider {

    private final AuthenticationService authenticationService;

    public UserAuthProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) {
        UserDto userDto=null;

        if (authentication instanceof UsernamePasswordAuthenticationToken)
            userDto=authenticationService.authenticate(
                    new CredentialsDto(
                            (String) authentication.getPrincipal(),
                            (char[]) authentication.getCredentials()
                    )
            );

        else if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            try {
                userDto=authenticationService.findByToken((String) authentication.getPrincipal());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (userDto==null)
            return null;


        return new UsernamePasswordAuthenticationToken(userDto,null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
