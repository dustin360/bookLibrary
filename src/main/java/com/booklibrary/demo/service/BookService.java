package com.booklibrary.demo.service;

import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.dto.PaginatedListDto;
import com.booklibrary.demo.dto.incoming.AddBooksToCategoryReq;
import com.booklibrary.demo.dto.incoming.CreateBookReq;
import com.booklibrary.demo.dto.incoming.UpdateBookReq;
import com.booklibrary.demo.dto.others.BookDto;
import com.booklibrary.demo.model.Book;
import com.booklibrary.demo.model.BookCategory;
import com.booklibrary.demo.model.Useraccount;

/**
 * Created by David on 12 Nov, 2021
 **/
public interface BookService {

    Book create(CreateBookReq req, Useraccount createdBy);

    Book update(UpdateBookReq req, Book book);

    Book delete(Book book);

    PaginatedListDto<BookDto> getAllBy(EntityStatus status, int page, int limit);

    void updateBookCategories(AddBooksToCategoryReq req);
}
