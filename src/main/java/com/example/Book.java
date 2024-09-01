package com.example;
class Book {

    private String isbn;
    private String title;
    private String author;
    private int publicationYear;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear; }

    public void validate() throws InvalidBookException {
        if (isbn == null || isbn.isEmpty()) {
            throw new InvalidBookException("ISBN cannot be null or empty.");
        }

        if (title == null || title.isEmpty()) {
            throw new InvalidBookException("Title cannot be null or empty.");
        }

        if (author == null || author.isEmpty()) {
            throw new InvalidBookException("Author cannot be null or empty.");
        }

        if (publicationYear <= 0) {
            throw new InvalidBookException("Publication year must be a positive number.");
        }
    }
}

class InvalidBookException extends Exception {
    public InvalidBookException(String message) {
        super(message);
    }
}