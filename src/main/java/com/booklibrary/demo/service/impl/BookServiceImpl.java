package com.booklibrary.demo.service.impl;

import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.constant.MessageConstant;
import com.booklibrary.demo.dto.PaginatedListDto;
import com.booklibrary.demo.dto.incoming.AddBooksToCategoryReq;
import com.booklibrary.demo.dto.incoming.CreateBookReq;
import com.booklibrary.demo.dto.incoming.UpdateBookReq;
import com.booklibrary.demo.dto.others.BookDto;
import com.booklibrary.demo.exception.BadRequestException;
import com.booklibrary.demo.exception.NotFoundException;
import com.booklibrary.demo.mapper.BookMapper;
import com.booklibrary.demo.model.Book;
import com.booklibrary.demo.model.BookCategory;
import com.booklibrary.demo.model.Useraccount;
import com.booklibrary.demo.repo.BookCategoryRepo;
import com.booklibrary.demo.repo.BookRepo;
import com.booklibrary.demo.service.BookService;
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
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;

    private BookCategoryRepo bookCategoryRepo;

    private ValidationService validationService;

    private ModelMapper mapper;

    @Override
    public Book create(CreateBookReq req, Useraccount createdBy) {

        // validation
        validationService.validateObject(req);

        // create
        Book book = new Book();
        book.setName(req.getName());
        book.setAuthor(req.getAuthor());
        book.setPublicationDate(req.getPublicationDate());
        book.setCreatedBy(createdBy.getEmail());
        book.setStatus(EntityStatus.ACTIVE);
        if (req.getBookCategoryId() != null && req.getBookCategoryId() > 0 ) {
            book.setBookCategory(bookCategoryRepo.findById(req.getBookCategoryId())
                .orElseThrow(() -> new NotFoundException(MessageConstant.CATEGORY_NOT_FOUND)));
        }

        return bookRepo.save(book);
    }

    @Override
    public Book update(UpdateBookReq req, Book book) {

        // validation
        validationService.validateObject(req);

        // update
        book.setName(req.getName());
        book.setAuthor(req.getAuthor());
        book.setPublicationDate(req.getPublicationDate());

        return bookRepo.save(book);
    }

    @Override
    public Book delete(Book book) {

        if (book.getStatus().equals(EntityStatus.DELETED)) {
            throw new BadRequestException("This book is already deleted");
        }
        book.setStatus(EntityStatus.DELETED);
        return bookRepo.save(book);
    }

    @Override
    public PaginatedListDto<BookDto> getAllBy(EntityStatus status, int page, int limit) {

        Page<Book> pageInfo = bookRepo.findAllByStatusOrderByCreatedAtDesc(
                status, PageRequest.of(page, limit));

        List<BookDto> books = pageInfo.getContent()
                .stream()
                .map(b -> BookMapper.mapToDto(b, mapper))
                .collect(Collectors.toList());

        return new PaginatedListDto<>(page, limit, pageInfo.getTotalElements(), books);
    }

    @Override
    public void updateBookCategories(AddBooksToCategoryReq req) {

        // validation
        validationService.validateObject(req);

        // book category
        BookCategory category = bookCategoryRepo.findById(req.getCategoryId())
                .orElseThrow(() -> new NotFoundException(MessageConstant.CATEGORY_NOT_FOUND));

        if (!category.getStatus().equals(EntityStatus.ACTIVE)) {
            throw new BadRequestException("Book category is NOT active");
        }

        // update books
        req.getBookIds().forEach(id -> {
            Book book = bookRepo.findById(id).orElseThrow(
                    () -> new NotFoundException("Book not found"));
            book.setBookCategory(category);
            bookRepo.save(book);
        });
    }
}
