package com.digital.library.project.librarymanagesys2dec.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class UserConfig{

    @Value("${user.authority.student}")
    String student_access;
    @Value("${user.authority.admin}")
    String admin_access;

    @Bean
    SecurityFilterChain getSecurityFilter(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/user/**").hasAnyRole("admin_access");
                    auth.requestMatchers("/student/getAllStudent/**").hasAnyAuthority("admin_access");
                    auth.requestMatchers("/student/createStudent/**").permitAll();
                    auth.requestMatchers("/student/**").hasAnyAuthority(student_access);
                    auth.anyRequest().authenticated();
                })
                .httpBasic(withDefaults())
                .csrf(csrf-> csrf.disable())
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    UserDetailsService getUserDetailsServiceBean(){
        return new UserService();
    }
    @Bean
    BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
