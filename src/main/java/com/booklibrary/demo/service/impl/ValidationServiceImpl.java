package com.booklibrary.demo.service.impl;

import com.booklibrary.demo.exception.BadRequestException;
import com.booklibrary.demo.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Service
@AllArgsConstructor
@Transactional
public class ValidationServiceImpl implements ValidationService {


    private Validator validator;

    @Override
    public void validateObject(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);

        for (ConstraintViolation<Object> violation : violations) {
            throw new BadRequestException(violation.getMessage());
        }

    }
}
