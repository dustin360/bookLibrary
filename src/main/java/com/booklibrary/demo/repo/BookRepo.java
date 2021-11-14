package com.booklibrary.demo.repo;

import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.model.Book;
import com.booklibrary.demo.model.Useraccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 12 Nov, 2021
 **/
public interface BookRepo extends JpaRepository<Book, Long> {

    Page<Book> findAllByStatusOrderByCreatedAtDesc(EntityStatus status, Pageable pageable);
}
