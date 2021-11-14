package com.booklibrary.demo.dto.incoming;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by David on 12 Nov, 2021
 **/
@Data
public class BookCategoryReq {

    @NotEmpty(message = "Category name is required")
    private String categoryName;
}
