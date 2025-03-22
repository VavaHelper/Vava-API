package com.helper.vavahelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.helper.vavahelper.repositories.UserRepository;

@Service
public class AuthorizathionService implements UserDetailsService {
    
    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Pesquisa para ver se o User já existe:
        return repository.findByLogin(username);
    }
    
}
