package com.booklibrary.demo.service;

import com.booklibrary.demo.model.Book;
import com.booklibrary.demo.model.Useraccount;

/**
 * Created by David on 14 Nov, 2021
 **/
public interface FavouriteBookService {

    void addFavouriteBook(Book book, Useraccount user);
}
