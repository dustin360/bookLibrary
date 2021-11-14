package com.booklibrary.demo.repo;

import com.booklibrary.demo.model.Useraccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 12 Nov, 2021
 **/
public interface UseraccountRepo extends JpaRepository<Useraccount, Long> {

    Useraccount findByEmail(String email);

    Boolean existsByEmail(String email);
}
