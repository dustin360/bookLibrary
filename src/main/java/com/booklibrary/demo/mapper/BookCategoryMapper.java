package com.booklibrary.demo.mapper;

import com.booklibrary.demo.dto.others.BookCateogryDto;
import com.booklibrary.demo.model.BookCategory;
import org.modelmapper.ModelMapper;

/**
 * Created by David on 12 Nov, 2021
 **/
public class BookCategoryMapper {

    public static BookCateogryDto mapToDto(BookCategory entity, ModelMapper mapper) {
        if (entity == null)
            return null;

        return mapper.map(entity, BookCateogryDto.class);
    }
}
