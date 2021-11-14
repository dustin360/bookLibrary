package com.booklibrary.demo.controller;

import com.booklibrary.demo.constant.ApiRoute;
import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.constant.MessageConstant;
import com.booklibrary.demo.dto.PaginatedListDto;
import com.booklibrary.demo.dto.incoming.BookCategoryReq;
import com.booklibrary.demo.dto.incoming.LoginDto;
import com.booklibrary.demo.dto.others.BookCateogryDto;
import com.booklibrary.demo.dto.others.UserDetailsDto;
import com.booklibrary.demo.exception.NotFoundException;
import com.booklibrary.demo.mapper.BookCategoryMapper;
import com.booklibrary.demo.model.BookCategory;
import com.booklibrary.demo.model.Useraccount;
import com.booklibrary.demo.repo.BookCategoryRepo;
import com.booklibrary.demo.repo.UseraccountRepo;
import com.booklibrary.demo.service.BookCategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by David on 12 Nov, 2021
 **/
@RestController
@AllArgsConstructor
@CrossOrigin
public class BookCategoryController {

    private BookCategoryService bookCategoryService;

    private BookCategoryRepo bookCategoryRepo;

    private UseraccountRepo useraccountRepo;

    private ModelMapper mapper;

    @PostMapping(value = ApiRoute.AUTH + ApiRoute.BOOK_CATEGORIES)
    public ResponseEntity<?> createBookCategory(Authentication auth,
                                                @RequestBody @Valid BookCategoryReq req) {

        Useraccount creator = useraccountRepo.findByEmail(auth.getName());

        BookCategory category = bookCategoryService.create(req, creator);

        return ResponseEntity.ok(BookCategoryMapper.mapToDto(category, mapper));
    }

    @PatchMapping(value = ApiRoute.AUTH + ApiRoute.BOOK_CATEGORIES + "/{bookCategoryId}")
    public ResponseEntity<?> updateBookCategory(long bookCategoryId,
                                                @RequestBody @Valid BookCategoryReq req) {

        BookCategory bookCategory = bookCategoryRepo.findById(bookCategoryId)
                .orElseThrow(() -> new NotFoundException(MessageConstant.CATEGORY_NOT_FOUND));

        BookCategory category = bookCategoryService.update(req, bookCategory);

        return ResponseEntity.ok(BookCategoryMapper.mapToDto(category, mapper));
    }

    @DeleteMapping(value = ApiRoute.AUTH + ApiRoute.BOOK_CATEGORIES + "/{bookCategoryId}")
    public ResponseEntity<?> deleteBookCategory(long bookCategoryId) {

        BookCategory bookCategory = bookCategoryRepo.findById(bookCategoryId)
                .orElseThrow(() -> new NotFoundException(MessageConstant.CATEGORY_NOT_FOUND));

        BookCategory category = bookCategoryService.delete(bookCategory);

        return ResponseEntity.ok(BookCategoryMapper.mapToDto(category, mapper));
    }


    @GetMapping(value = ApiRoute.AUTH + ApiRoute.BOOK_CATEGORIES)
    public ResponseEntity<?> getAllBookCategories(@RequestParam("ACTIVE") EntityStatus status,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int limit) {

        PaginatedListDto<BookCateogryDto> list = bookCategoryService.get(status, page, limit);

        return ResponseEntity.ok(list);
    }
}
