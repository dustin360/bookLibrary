package com.booklibrary.demo.controller;

import com.booklibrary.demo.constant.ApiRoute;
import com.booklibrary.demo.dto.incoming.LoginDto;
import com.booklibrary.demo.dto.others.UserDetailsDto;
import com.booklibrary.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by David on 12 Nov, 2021
 **/
@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private AuthService authService;

    @PostMapping(value = ApiRoute.LOGIN)
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {

        UserDetailsDto dto = authService.login(loginDto);
        return ResponseEntity.accepted().body(dto);
    }
}
