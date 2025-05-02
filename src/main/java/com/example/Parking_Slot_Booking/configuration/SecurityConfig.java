package com.example.Parking_Slot_Booking.configuration;

import com.example.Parking_Slot_Booking.security.JwtFilter;
import com.example.Parking_Slot_Booking.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         return http
                .csrf(csrf -> csrf.disable())
                 .authorizeHttpRequests(req ->
                         req.antMatchers("/auth/login", "/auth/register").permitAll()
                                 .antMatchers("/admin/delete-slot/*").hasRole("SUPER_ADMIN")
                                 .antMatchers("/admin/**").hasRole("ADMIN")
                                 .antMatchers("/parking/**").hasAnyRole("USER", "ADMIN")
                                 .anyRequest().authenticated()
                 )

                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                 .sessionManagement(session -> session
                         .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public ProviderManager authenticationManager(){
        return new ProviderManager(authenticationProvider());
    }
}
