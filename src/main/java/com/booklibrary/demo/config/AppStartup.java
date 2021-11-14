package com.booklibrary.demo.config;

import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.constant.RoleConstant;
import com.booklibrary.demo.model.Useraccount;
import com.booklibrary.demo.repo.UseraccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by David on 12 Nov, 2021
 **/
@Component
@Transactional
public class AppStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired private UseraccountRepo useraccountRepo;
    @Autowired private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createDefaultUser();
    }

    private void createDefaultUser() {
        if (!useraccountRepo.existsByEmail("tom@gmail.com")) {
            Useraccount user = new Useraccount();
            user.setFullName("Tom Brody");
            user.setEmail("tom@gmail.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setStatus(EntityStatus.ACTIVE);
            user.setRole(RoleConstant.INDIVIDUAL_USER);
            useraccountRepo.save(user);
        }

    }
}
