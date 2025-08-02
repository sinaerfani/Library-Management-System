package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.BookSaveDto;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.service.BookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Book> saveBook(@RequestBody @Valid BookSaveDto bookSaveDto) {
        Book book = new Book();
        book.setName(bookSaveDto.getName());
        book.setAuthor(bookSaveDto.getAuthor());
        book.setCount(bookSaveDto.getCount());
        bookService.saveBook(book);
        return ResponseEntity.ok(book);
    }
    @GetMapping("/allBooks")
    public List<Book>getAllBooks(){
       return bookService.getAllBooks();
    }
    @GetMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteBook(@RequestParam int id){
        bookService.deleteBook(id);
    }
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void updateBook(@RequestParam int id,@RequestBody @Valid BookSaveDto bookSaveDto){
        bookService.update(id,bookSaveDto);
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER')")
    public String user() {
        return "user";
    }

}






