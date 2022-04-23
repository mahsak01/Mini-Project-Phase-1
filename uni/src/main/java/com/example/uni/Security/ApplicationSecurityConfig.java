package com.example.uni.Security;

import com.example.uni.Dto.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {



    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    @Autowired
    public ApplicationSecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint) {
        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new UsernamePasswordAuthFilter() , BasicAuthenticationFilter.class)
                .addFilterBefore(new CookieAuthFilter(),UsernamePasswordAuthFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout().deleteCookies(CookieAuthFilter.COOKIE_NAME)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/colleges","/api/v1/lessons").hasAnyRole(Roles.MANAGER.name())
                .antMatchers("/api/v1/**").hasAnyRole(Roles.ADMIN.name())
                .anyRequest()
                .authenticated();


    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails userAdmin = User.builder()
//                .username("admin")
//                .roles(Roles.ADMIN.name());
//
//
//
//        return new InMemoryUserDetailsManager(
//          userAdmin
//        );
//    }
}
