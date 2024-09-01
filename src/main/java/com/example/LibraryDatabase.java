package com.example;

import java.util.HashMap;
import java.util.Map;

public class LibraryDatabase {

    private Map<String, Book> books = new HashMap<>();
    public String addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            return "Book with ISBN " + book.getIsbn() + " already exists.";
        }

        try {
            book.validate();
            books.put(book.getIsbn(), book); 
            return "Book added successfully.";
        } catch (InvalidBookException e) {
            return   e.getMessage();
        }
    }
    
    public Book getBookByIsbn(String isbn) {
        return books.get(isbn);
    }
}