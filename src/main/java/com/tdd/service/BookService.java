package com.tdd.service;

import com.tdd.models.Book;

import java.util.List;

/**
 * Created by wuxinjian on 2017/3/6.
 */
public interface BookService {

    List<Book> getAllBooks();

    Book getBook(String isbn);

    String addBook(Book book);

    String updateBook(Book book);
}
