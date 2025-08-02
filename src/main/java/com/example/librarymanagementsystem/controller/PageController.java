package com.example.librarymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/newBook")
    public String newBook() {
        return "newBook";
    }

    @GetMapping("/booksList")
    public String booksList() {
        return "booksList";
    }
}