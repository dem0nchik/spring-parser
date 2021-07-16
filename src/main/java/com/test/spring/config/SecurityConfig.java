package com.test.spring.config;

import com.test.spring.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.test.spring")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/sign_up", "/login").anonymous()
               .antMatchers("/").permitAll()
               .antMatchers("/parse", "/results", "/view_result").authenticated()
           .and()
               .csrf().disable()
               .formLogin()
               .loginPage("/login")
               .loginProcessingUrl("/login/process")
               .usernameParameter("username")
               .defaultSuccessUrl("/parse")
               .failureUrl("/login?error=true")
           .and()
               .exceptionHandling()
               .accessDeniedPage("/")
           .and()
               .logout();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
