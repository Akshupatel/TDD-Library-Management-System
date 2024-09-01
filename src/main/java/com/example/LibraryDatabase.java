package com.example;

import java.util.HashMap;
import java.util.Map;

public class LibraryDatabase {

    private Map<String, Book> books = new HashMap<>();
    private Map<String, Boolean> bookAvailability = new HashMap<>();
    public String addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            return "Book with ISBN " + book.getIsbn() + " already exists.";
        }

        try {
            book.validate();
            books.put(book.getIsbn(), book); 
            bookAvailability.put(book.getIsbn(), true);
            return "Book added successfully.";
        } catch (InvalidBookException e) {
            return   e.getMessage();
        }
    }
    
    public String borrowBook(String isbn) {
        if (!books.containsKey(isbn)) {
            return "Book with ISBN " + isbn + " does not exist in the library.";
        }
        
        // Handle null check before accessing the value
        Boolean isAvailable = bookAvailability.get(isbn);
        if (isAvailable == null || !isAvailable) {
            return "Book with ISBN " + isbn + " is currently unavailable.";
        }
        
        bookAvailability.put(isbn, false); // Mark the book as borrowed
        return "Book borrowed successfully.";
    }

    public Book getBookByIsbn(String isbn) {
        return books.get(isbn);
    }
}