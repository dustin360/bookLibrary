package com.booklibrary.demo.dto.others;

import com.booklibrary.demo.constant.DateTimeEnum;
import com.booklibrary.demo.constant.EntityStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by David on 12 Nov, 2021
 **/
@Data
public class BookCateogryDto {

    private long id;

    private String name;

    private String status;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeEnum.DATE_TIME_PATTERN)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeEnum.DATE_TIME_PATTERN)
    private LocalDateTime updatedAt;
}
