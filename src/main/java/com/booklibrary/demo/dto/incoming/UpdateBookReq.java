package com.booklibrary.demo.dto.incoming;

import com.booklibrary.demo.constant.DateTimeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by David on 13 Nov, 2021
 **/
@Data
public class UpdateBookReq {

    @NotEmpty(message = "Book category name is required")
    private String name;

    @NotEmpty(message = "Author name is required")
    private String author;

    @NotNull(message = "Publication date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeEnum.DATE_PATTERN)
    private LocalDate publicationDate;

}
