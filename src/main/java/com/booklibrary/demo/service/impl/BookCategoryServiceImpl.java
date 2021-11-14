package com.booklibrary.demo.service.impl;

import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.constant.MessageConstant;
import com.booklibrary.demo.dto.PaginatedListDto;
import com.booklibrary.demo.dto.incoming.BookCategoryReq;
import com.booklibrary.demo.dto.others.BookCateogryDto;
import com.booklibrary.demo.exception.BadRequestException;
import com.booklibrary.demo.exception.EntityAlreadyExistsException;
import com.booklibrary.demo.mapper.BookCategoryMapper;
import com.booklibrary.demo.model.BookCategory;
import com.booklibrary.demo.model.Useraccount;
import com.booklibrary.demo.repo.BookCategoryRepo;
import com.booklibrary.demo.service.BookCategoryService;
import com.booklibrary.demo.service.ValidationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by David on 12 Nov, 2021
 **/
@Service
@Transactional
@AllArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {

    private BookCategoryRepo bookCategoryRepo;
    private ValidationService validationService;
    private ModelMapper mapper;

    @Override
    public BookCategory create(BookCategoryReq request, Useraccount createdBy) {

        // validations
        validationService.validateObject(request);

        if (bookCategoryRepo.existsByName(request.getCategoryName())) {
            throw new EntityAlreadyExistsException(MessageConstant.CATEGORY_EXISTS);
        }

        // create
        BookCategory category = new BookCategory();
        category.setName(request.getCategoryName());
        category.setStatus(EntityStatus.ACTIVE);
        category.setCreatedBy(createdBy.getEmail());

        return bookCategoryRepo.save(category);
    }

    @Override
    public BookCategory update(BookCategoryReq request, BookCategory bCategory) {

        // validation
        validationService.validateObject(request);

        bCategory.setName(request.getCategoryName());
        return bookCategoryRepo.save(bCategory);
    }

    @Override
    public BookCategory delete(BookCategory bCategory) {

        if (bCategory.getStatus().equals(EntityStatus.DELETED)) {
            throw new BadRequestException("Book category is already deleted");
        }

        bCategory.setStatus(EntityStatus.DELETED);
        return bookCategoryRepo.save(bCategory);
    }

    @Override
    public PaginatedListDto<BookCateogryDto> get(EntityStatus status, int page, int limit) {

        Page<BookCategory> pageInfo = bookCategoryRepo.findAllByStatusOrderByCreatedAtDesc(
                status, PageRequest.of(page, limit));

        List<BookCateogryDto> bookCategoryList = pageInfo.getContent()
                .stream()
                .map(b -> BookCategoryMapper.mapToDto(b, mapper))
                .collect(Collectors.toList());

        return new PaginatedListDto<>(page, limit, pageInfo.getTotalElements(), bookCategoryList);
    }
}
