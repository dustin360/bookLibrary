package com.booklibrary.demo.dto.incoming;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by David on 14 Nov, 2021
 **/
@Data
public class AddBooksToCategoryReq {

    @NotNull(message = "Book category ID is required")
    private Long categoryId;

    @NotNull(message = "Book IDs cannot be null")
    @NotEmpty(message = "Book IDs cannot be empty")
    private List<Long> bookIds;
}
