package com.booklibrary.demo.repo;

import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.model.BookCategory;
import com.booklibrary.demo.model.Useraccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 12 Nov, 2021
 **/
public interface BookCategoryRepo extends JpaRepository<BookCategory, Long> {

    Boolean existsByName(String name);

    Page<BookCategory> findAllByStatusOrderByCreatedAtDesc(EntityStatus status, Pageable pageable);
}
