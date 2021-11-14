package com.booklibrary.demo.service;

import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.dto.PaginatedListDto;
import com.booklibrary.demo.dto.incoming.BookCategoryReq;
import com.booklibrary.demo.dto.others.BookCateogryDto;
import com.booklibrary.demo.model.BookCategory;
import com.booklibrary.demo.model.Useraccount;

/**
 * Created by David on 12 Nov, 2021
 **/
public interface BookCategoryService {

    BookCategory create(BookCategoryReq request, Useraccount createdBy);

    BookCategory update(BookCategoryReq request, BookCategory bCategory);

    BookCategory delete(BookCategory bCategory);

    PaginatedListDto<BookCateogryDto> get(EntityStatus status,
                                          int page, int limit);
}
