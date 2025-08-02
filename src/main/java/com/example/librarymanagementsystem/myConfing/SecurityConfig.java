package com.example.librarymanagementsystem.myConfing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails= User.withUsername("admin")
                .password(passwordEncoder().encode("23456"))
                .roles("ADMIN")
                .build();

        UserDetails userDetails1= User.withUsername("user")
                .password(passwordEncoder().encode("23456"))
                .roles("USER")
                .build();
               return new InMemoryUserDetailsManager(userDetails,userDetails1);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity.authorizeHttpRequests(auth->
                auth.requestMatchers("/").permitAll()
                        .anyRequest().authenticated())
                .formLogin((formLoginConfigurer)-> formLoginConfigurer.permitAll())
                .build();

    }
}
