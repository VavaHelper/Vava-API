package com.helper.vavahelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.helper.vavahelper.repositories.UserRepository;

public class AuthorizathionService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Pesquisa para ver se o User já existe:
        return userRepository.findByLogin(username);
    }
    
}
