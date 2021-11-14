package com.booklibrary.demo.dto.others;

import com.booklibrary.demo.constant.DateTimeEnum;
import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.model.BookCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by David on 12 Nov, 2021
 **/
@Data
public class BookDto {

    private long id;

    private String name;

    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeEnum.DATE_PATTERN)
    private LocalDate publicationDate;

    private String createdBy;

    private String status;

    private BookCateogryDto bookCategory;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeEnum.DATE_TIME_PATTERN)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeEnum.DATE_TIME_PATTERN)
    private LocalDateTime updatedAt;
}
