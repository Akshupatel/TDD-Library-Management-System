// validate book for succesfully add 
package com.example;

public  class BookValidator {

    public static void validate(Book book) throws InvalidBookException {
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            throw new InvalidBookException("ISBN cannot be null or empty.");
        }

        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new InvalidBookException("Title cannot be null or empty.");
        }

        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new InvalidBookException("Author cannot be null or empty.");
        }

        if (book.getPublicationYear() <= 0) {
            throw new InvalidBookException("Publication year must be a positive number.");
        }
    }
}
