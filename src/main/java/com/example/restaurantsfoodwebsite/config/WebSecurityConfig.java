package com.example.restaurantsfoodwebsite.config;

import com.example.restaurantsfoodwebsite.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .formLogin()
                .loginPage("/loginPage")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .failureUrl("/loginPage?error=true")
                .defaultSuccessUrl("/loginSuccess")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/users").authenticated()
                .antMatchers("/users/add").permitAll()
                .antMatchers("/users/delete").hasAuthority(UserRole.MANAGER.name())
                .antMatchers("/manager").hasAuthority(UserRole.MANAGER.name())
                .antMatchers("/customer").hasAuthority(UserRole.CUSTOMER.name())
                .antMatchers("/restaurantOwner").hasAuthority(UserRole.RESTAURANT_OWNER.name())
                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
