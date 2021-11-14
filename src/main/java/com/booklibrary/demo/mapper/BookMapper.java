package com.booklibrary.demo.mapper;

import com.booklibrary.demo.dto.others.BookDto;
import com.booklibrary.demo.model.Book;
import org.modelmapper.ModelMapper;

/**
 * Created by David on 12 Nov, 2021
 **/
public class BookMapper {

    public static BookDto mapToDto(Book entity, ModelMapper mapper) {
        if (entity == null)
            return null;

        BookDto dto =  mapper.map(entity, BookDto.class);
        dto.setBookCategory(BookCategoryMapper.mapToDto(entity.getBookCategory(), mapper));
        return dto;
    }
}
