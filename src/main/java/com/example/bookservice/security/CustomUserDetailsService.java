package com.example.bookservice.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Mặc định cấp quyền "USER"
        return User.withUsername(username)
                .password("") // Bỏ qua mật khẩu
                .roles("USER") // Tạo ROLE_USER
                .build();
    }
}

