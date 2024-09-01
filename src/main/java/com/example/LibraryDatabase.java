package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class LibraryDatabase {

    private Map<String, Book> books = new HashMap<>();
    private Map<String, Boolean> bookAvailability = new HashMap<>();

    // function for add book
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
    
    // function for borrow book
    public String borrowBook(String isbn) {
        if (!books.containsKey(isbn)) {
            return "Book with ISBN " + isbn + " does not exist in the library.";
        }
        Boolean isAvailable = bookAvailability.get(isbn);
        if (isAvailable == null || !isAvailable) {
            return "Book with ISBN " + isbn + " is currently unavailable.";
        }
        
        bookAvailability.put(isbn, false); // Mark the book as borrowed
        return "Book borrowed successfully.";
    }
    
    //funtion for return book
    public String returnBook(String isbn) {
        if (!books.containsKey(isbn)) {
            return "Book with ISBN " + isbn + " does not exist in the library.";
        }

        Boolean isAvailable = bookAvailability.get(isbn);
        if (isAvailable == null || isAvailable) {
            return "Book with ISBN " + isbn + " was not borrowed.";
        }

        bookAvailability.put(isbn, true); // Mark the book as available
        return "Book returned successfully.";
    }
    
    // Function to get a list of available books
    public List<Book> getAvailableBooks() {
            List<Book> availableBooks = new ArrayList<>();
            for (Map.Entry<String, Boolean> entry : bookAvailability.entrySet()) {
                if (entry.getValue()) { // Check if the book is available
                    availableBooks.add(books.get(entry.getKey()));
                }
            }
            return availableBooks;
        }
    
    public Book getBookByIsbn(String isbn) {
        return books.get(isbn);
    }
}