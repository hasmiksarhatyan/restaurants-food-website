package com.example.restaurantsfoodwebsite.security;

import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userByEmail = userRepository.findByEmail(username);
        if (!userByEmail.isPresent()) {
            throw new UsernameNotFoundException("Username does not exist");
        }
        return new CurrentUser(userByEmail.get());
    }
}
