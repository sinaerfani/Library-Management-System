package com.example.librarymanagementsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class BookSaveDto {

    private String name;


    private String author;


    private int count;

    @NotNull(message = "name.is.null")
    @NotEmpty(message = "name.is.empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "author.is.null")
    @NotEmpty(message = "author.is.empty")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Min(value = 1, message = "count.must.be.positive")
    @NotNull(message = "count.is.null")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
