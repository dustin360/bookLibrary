package com.booklibrary.demo.service.impl;

import com.booklibrary.demo.model.Book;
import com.booklibrary.demo.model.FavouriteBook;
import com.booklibrary.demo.model.Useraccount;
import com.booklibrary.demo.repo.FavouriteBookRepo;
import com.booklibrary.demo.service.FavouriteBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by David on 14 Nov, 2021
 **/
@Service
@AllArgsConstructor
@Transactional
public class FavouriteBookServiceImpl implements FavouriteBookService {

    private FavouriteBookRepo favouriteBookRepo;


    @Override
    public void addFavouriteBook(Book book, Useraccount user) {

        if (!favouriteBookRepo.existsByUseraccountAndBook(user, book)) {
            FavouriteBook favouriteBook = new FavouriteBook();
            favouriteBook.setUseraccount(user);
            favouriteBook.setBook(book);
            favouriteBookRepo.save(favouriteBook);
        }

    }
}
