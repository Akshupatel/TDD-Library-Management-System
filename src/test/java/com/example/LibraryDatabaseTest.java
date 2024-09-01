package com.example;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LibraryDatabaseTest {
       public LibraryDatabaseTest(){
        System.out.println("hello");
       }
    @Test
    public void addBook_validBook_shouldAddSuccessfully() {
        LibraryDatabase database = new LibraryDatabase();
        Book book = new Book("1234567890", "Effective Java", "Joshua Bloch", 2018);

        String result = database.addBook(book);

        assertEquals("Book added successfully.", result);
    }

    @Test
    public void addBook_duplicateIsbn_shouldReturnErrorMessage() {
        LibraryDatabase database = new LibraryDatabase();
        Book book1 = new Book("1234567890", "Effective Java", "Joshua Bloch", 2018);
        Book book2 = new Book("1234567890", "Another Book", "Different Author", 2023);
        database.addBook(book1);
        String result = database.addBook(book2);
        assertEquals("Book with ISBN 1234567890 already exists.", result);
    }

    @Test
    public void addBook_invalidIsbn_shouldReturnErrorMessage() {
        LibraryDatabase database = new LibraryDatabase();
        Book book = new Book(null, "Effective Java", "Joshua Bloch", 2018);
        String result = database.addBook(book);
        assertEquals("ISBN cannot be null or empty.", result);
    }

    @Test
    public void addBook_missingTitle_shouldReturnErrorMessage() {
        LibraryDatabase database = new LibraryDatabase();
        Book book = new Book("1234567890", "", "Joshua Bloch", 2018);
        String result = database.addBook(book);
        assertEquals("Title cannot be null or empty.", result);
    }

    @Test
    public void addBook_missingAuthor_shouldReturnErrorMessage() {
        LibraryDatabase database = new LibraryDatabase();
        Book book = new Book("1234567890", "Effective Java", "", 2018);
        String result = database.addBook(book);
        assertEquals("Author cannot be null or empty.", result);
    }

    @Test
    public void addBook_invalidPublicationYear_shouldReturnErrorMessage() {
        LibraryDatabase database = new LibraryDatabase();
        Book book = new Book("1234567890", "Effective Java", "Joshua Bloch", 0);
        String result = database.addBook(book);
        assertEquals("Publication year must be a positive number.", result);
    }
 
    @Test
    public void borrowBook_bookAvailable_shouldBorrowSuccessfully() {
        LibraryDatabase database = new LibraryDatabase();
        Book book = new Book("7874428560", "Effective Java", "Joshua Bloch", 2018);
        database.addBook(book);
        String result = database.borrowBook("7874428560");
        assertEquals("Book borrowed successfully.", result);
    }

    @Test
    public void borrowBook_bookNotAvailable_shouldReturnErrorMessage() {
        LibraryDatabase database = new LibraryDatabase();
        Book book = new Book("1234567890", "Effective Java", "Joshua Bloch", 2018);
        database.addBook(book);
        database.borrowBook("1234567890"); // First borrow to make it unavailable

        String result = database.borrowBook("1234567890");

        assertEquals("Book with ISBN 1234567890 is currently unavailable.", result);
    }

    @Test
    public void borrowBook_bookDoesNotExist_shouldReturnErrorMessage() {
        LibraryDatabase database = new LibraryDatabase();

        String result = database.borrowBook("0000000000");

        assertEquals("Book with ISBN 0000000000 does not exist in the library.", result);
    }
  
}