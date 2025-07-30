package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.BookSaveDto;
import com.example.librarymanagementsystem.model.Book;

import java.util.List;

public interface BookService {
    void saveBook(Book book);
    void deleteBook(int id);
    List<Book> getAllBooks();
    void update(int id, BookSaveDto bookSaveDto);

}
