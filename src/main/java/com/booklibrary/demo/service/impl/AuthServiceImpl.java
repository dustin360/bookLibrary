package com.booklibrary.demo.service.impl;

import com.booklibrary.demo.dto.incoming.LoginDto;
import com.booklibrary.demo.dto.others.UserDetailsDto;
import com.booklibrary.demo.model.Useraccount;
import com.booklibrary.demo.repo.UseraccountRepo;
import com.booklibrary.demo.security.TokenProvider;
import com.booklibrary.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by David on 12 Nov, 2021
 **/
@Service
@AllArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private UseraccountRepo useraccountRepo;
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;

    @Override
    public UserDetailsDto login(LoginDto loginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail().trim(),
                        loginDto.getPassword().trim()));

        Useraccount acct = useraccountRepo.findByEmail(authentication.getName());


        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);

        // prepare response
        UserDetailsDto dto = new UserDetailsDto();
        dto.setToken(token);
        dto.setEmail(acct.getEmail());
        dto.setRole(acct.getRole().name());
        dto.setFullName(acct.getFullName());

        return dto;
    }
}
