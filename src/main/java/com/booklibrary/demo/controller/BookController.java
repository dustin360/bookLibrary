package com.booklibrary.demo.controller;

import com.booklibrary.demo.constant.ApiRoute;
import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.dto.GeneralResponse;
import com.booklibrary.demo.dto.PaginatedListDto;
import com.booklibrary.demo.dto.incoming.AddBooksToCategoryReq;
import com.booklibrary.demo.dto.incoming.BookCategoryReq;
import com.booklibrary.demo.dto.incoming.CreateBookReq;
import com.booklibrary.demo.dto.incoming.UpdateBookReq;
import com.booklibrary.demo.dto.others.BookCateogryDto;
import com.booklibrary.demo.dto.others.BookDto;
import com.booklibrary.demo.exception.NotFoundException;
import com.booklibrary.demo.mapper.BookCategoryMapper;
import com.booklibrary.demo.mapper.BookMapper;
import com.booklibrary.demo.model.Book;
import com.booklibrary.demo.model.BookCategory;
import com.booklibrary.demo.model.Useraccount;
import com.booklibrary.demo.repo.BookCategoryRepo;
import com.booklibrary.demo.repo.BookRepo;
import com.booklibrary.demo.repo.UseraccountRepo;
import com.booklibrary.demo.service.BookCategoryService;
import com.booklibrary.demo.service.BookService;
import com.booklibrary.demo.service.FavouriteBookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
public class BookController {


    private BookService bookService;

    private BookRepo bookRepo;

    private UseraccountRepo useraccountRepo;

    private FavouriteBookService favouriteBookService;

    private ModelMapper mapper;

    @PostMapping(value = ApiRoute.AUTH + ApiRoute.BOOKS)
    public ResponseEntity<?> createBook(Authentication auth,
                                        @RequestBody @Valid CreateBookReq req) {

        Useraccount creator = useraccountRepo.findByEmail(auth.getName());

        Book book = bookService.create(req, creator);

        return ResponseEntity.ok(BookMapper.mapToDto(book, mapper));
    }

    @PatchMapping(value = ApiRoute.AUTH + ApiRoute.BOOKS + "/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId,
                                        @RequestBody @Valid UpdateBookReq req) {

        Book book = bookRepo.findById(bookId).orElseThrow(
                () -> new NotFoundException("Book not found"));

        Book updatedBook = bookService.update(req, book);

        return ResponseEntity.ok(BookMapper.mapToDto(updatedBook, mapper));
    }

    @DeleteMapping(value = ApiRoute.AUTH + ApiRoute.BOOKS + "/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {

        Book book = bookRepo.findById(bookId).orElseThrow(
                () -> new NotFoundException("Book not found"));

        Book deletedBook = bookService.delete(book);

        return ResponseEntity.ok(BookMapper.mapToDto(deletedBook, mapper));
    }

    @GetMapping(value = ApiRoute.AUTH + ApiRoute.BOOKS)
    public ResponseEntity<?> getAllBooks(@RequestParam("ACTIVE") EntityStatus status,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int limit) {

        PaginatedListDto<BookDto> list = bookService.getAllBy(status, page, limit);

        return ResponseEntity.ok(list);
    }


    @PatchMapping(value = ApiRoute.AUTH + ApiRoute.BOOKS + ApiRoute.REASSIGN_CATEGORIES)
    public ResponseEntity<?> updateCategoryForBooks(@RequestBody @Valid AddBooksToCategoryReq req) {


        bookService.updateBookCategories(req);

        return ResponseEntity.ok(null);
    }



    @PutMapping(value = ApiRoute.AUTH + ApiRoute.BOOKS + ApiRoute.FAVOURITES + "/{bookId}")
    public ResponseEntity<?> addBookAsFavourite(Authentication auth,
                                                @PathVariable Long bookId) {

        Book book = bookRepo.findById(bookId).orElseThrow(
                () -> new NotFoundException("Book not found"));

        Useraccount user = useraccountRepo.findByEmail(auth.getName());

        favouriteBookService.addFavouriteBook(book, user);

        return ResponseEntity.ok(
                new GeneralResponse(HttpStatus.OK.value(),
                        "Book added as favourite"));
    }

}
