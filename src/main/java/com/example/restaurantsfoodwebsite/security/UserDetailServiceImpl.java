package com.example.restaurantsfoodwebsite.security;

import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userByEmail = userRepository.findByEmail(username);
        if (userByEmail.isEmpty()) {
            throw new UsernameNotFoundException("Username does not exist");
        }
        return new CurrentUser(userByEmail.get());
    }
}
