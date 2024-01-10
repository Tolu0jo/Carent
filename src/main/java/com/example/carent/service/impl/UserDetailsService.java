package com.example.carent.service.impl;

import com.example.carent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



    @Service
    @RequiredArgsConstructor
    public class UserDetailsService {
        private final UserRepository userRepository;


        public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
            return new org.springframework.security.core.userdetails.UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                    return userRepository.findByEmail(email).get();
                }
            };
        }
    }

