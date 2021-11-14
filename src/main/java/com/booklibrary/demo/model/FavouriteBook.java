package com.booklibrary.demo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by David on 14 Nov, 2021
 **/
@Data
@Entity
public class FavouriteBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Useraccount useraccount;
}
