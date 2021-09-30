package com.example.Bookstore.web;

import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User curr_user = userRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curr_user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(curr_user.getRole()));
        return user;
    }
}
