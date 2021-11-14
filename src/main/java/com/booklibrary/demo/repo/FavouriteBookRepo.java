package com.booklibrary.demo.repo;

import com.booklibrary.demo.model.Book;
import com.booklibrary.demo.model.FavouriteBook;
import com.booklibrary.demo.model.Useraccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 14 Nov, 2021
 **/
public interface FavouriteBookRepo extends JpaRepository<FavouriteBook, Long> {

    Boolean existsByUseraccountAndBook(Useraccount user, Book book);
}
