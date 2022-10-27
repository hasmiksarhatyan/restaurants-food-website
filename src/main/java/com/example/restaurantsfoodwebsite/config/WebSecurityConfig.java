package com.example.restaurantsfoodwebsite.config;

import com.example.restaurantsfoodwebsite.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/loginPage")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .failureUrl("/loginPage?error=true")
                .defaultSuccessUrl("/users/home")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/loginPage").permitAll()
                .antMatchers("/users/add").permitAll()
                .antMatchers("/restaurants").permitAll()
                .antMatchers("/restaurants/indicatedRestaurant").permitAll()
                .antMatchers("/restaurants/add").authenticated()
                .antMatchers("/restaurants/myRestaurants").hasAuthority(Role.RESTAURANT_OWNER.name())
                .antMatchers("/users/home").authenticated()
                .antMatchers("/restaurantsCategory").permitAll()
//                .antMatchers("/users").hasAuthority(Role.MANAGER.name())
                .antMatchers("/users/delete").hasAuthority(Role.MANAGER.name())
                .antMatchers("/manager/**").hasAuthority(Role.MANAGER.name())
                .antMatchers("/restaurantsCategory/add").authenticated()
                .anyRequest().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
