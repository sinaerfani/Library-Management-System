package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.BookSaveDto;
import com.example.librarymanagementsystem.exception.RuleException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepo bookRepo;
    @Override
    public void saveBook(Book book) {
       bookRepo.save(book);
    }

    @Override
    public void deleteBook(int id) {
         bookRepo.deleteById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public void update(int id, BookSaveDto updatedBook) {
        Optional<Book> optionalBook=bookRepo.findById(id);
        Book book=optionalBook.orElseThrow(()->new RuleException("book.not.found"));
        book.setName(updatedBook.getName());
        book.setAuthor(updatedBook.getAuthor());
        book.setCount(updatedBook.getCount());
        bookRepo.save(book);
    }
}
