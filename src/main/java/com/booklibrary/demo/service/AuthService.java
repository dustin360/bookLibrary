package com.booklibrary.demo.service;

import com.booklibrary.demo.dto.incoming.LoginDto;
import com.booklibrary.demo.dto.others.UserDetailsDto;

/**
 * Created by David on 12 Nov, 2021
 **/
public interface AuthService {
    UserDetailsDto login(LoginDto loginDto);
}
