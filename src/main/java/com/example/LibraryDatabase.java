// functinality for add ,borrow ,return and display book 
package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class LibraryDatabase {

    private final Map<String, Book> books = new HashMap<>();

    public String addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            return "Book with ISBN " + book.getIsbn() + " already exists.";
        }
        try {
            BookValidator.validate(book);
            books.put(book.getIsbn(), book);
            return "Book added successfully.";
        } catch (InvalidBookException e) {
            return e.getMessage();
        }
    }

    public String borrowBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return "Book with ISBN " + isbn + " does not exist in the library.";
        }
        if (!book.isAvailable()) {
            return "Book with ISBN " + isbn + " is currently unavailable.";
        }
        book.setAvailable(false);
        return "Book borrowed successfully.";
    }

    public String returnBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return "Book with ISBN " + isbn + " does not exist in the library.";
        }
        if (book.isAvailable()) {
            return "Book with ISBN " + isbn + " was not borrowed.";
        }
        book.setAvailable(true);
        return "Book returned successfully.";
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}
