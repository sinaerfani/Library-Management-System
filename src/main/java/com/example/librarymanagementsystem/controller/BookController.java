package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.BookSaveDto;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.service.BookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody @Valid BookSaveDto bookSaveDto){
        Book book=new Book();
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
    public void deleteBook(@RequestParam int id){
        bookService.deleteBook(id);
    }
    @PostMapping("/update")
    public void updateBook(@RequestParam int id,@RequestBody @Valid BookSaveDto bookSaveDto){
        bookService.update(id,bookSaveDto);
    }


}






