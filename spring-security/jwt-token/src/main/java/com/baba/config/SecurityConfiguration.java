package com.baba.config;

import com.baba.services.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.baba.entities.Permission.*;
import static com.baba.entities.Role.ADMIN;
import static com.baba.entities.Role.USER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    @Autowired
    private final JWTAuthenticationFilter jwtAuthFilter;
    @Autowired
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    private LogoutService logoutService;
    @Autowired
    InvalidUserAuthEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
//                        .requestMatchers(GET,"/admin/**").hasAuthority(ADMIN_READ.name())
//                        .requestMatchers(POST,"/admin/**").hasAuthority(ADMIN_CREATE.name())
//                        .requestMatchers(PUT,"/admin/**").hasAuthority(ADMIN_UPDATE.name())
//                        .requestMatchers(DELETE,"/admin/**").hasAuthority(ADMIN_DELETE.name())
                        .requestMatchers("/user/**").hasRole(USER.name())
                        .requestMatchers(GET, "/user/**").hasAuthority(USER_READ.name())
                        .requestMatchers(POST, "/user/**").hasAuthority(USER_CREATE.name())
                        .requestMatchers(PUT, "/user/**").hasAuthority(USER_UPDATE.name())
                        .requestMatchers(DELETE, "/user/**").hasAuthority(USER_DELETE.name())
                        .anyRequest().authenticated())
                .exceptionHandling(error -> error.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout((logout) -> logout.logoutUrl("/logout").permitAll()
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler((request, response, authentication) ->
                                SecurityContextHolder.clearContext()));

        return httpSecurity.build();
    }
}
