package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab2Test {

    @Test
    void searchBookEveryStatementTest() {
        Library lib = new Library();
        lib.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        lib.addBook(new Book("Clean Code", "Another Author", "Programming"));
        lib.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));

        List<Book> foundBooks = lib.searchBookByTitle("Clean Code");
        assertNotNull(foundBooks);
        assertEquals(2, foundBooks.size());
        assertEquals("Clean Code", foundBooks.get(0).getTitle());
        assertEquals("Clean Code", foundBooks.get(1).getTitle());

        List<Book> missingBooks = lib.searchBookByTitle("Unknown Book");
        assertNull(missingBooks);

        assertThrows(IllegalArgumentException.class, () -> lib.searchBookByTitle(""));
    }

    @Test
    void searchBookMultipleConditionTest() {
        Library titleMatchesAndAvailable = new Library();
        titleMatchesAndAvailable.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        List<Book> availableResult = titleMatchesAndAvailable.searchBookByTitle("Clean Code");
        assertNotNull(availableResult);
        assertEquals(1, availableResult.size());

        Library titleMatchesAndBorrowed = new Library();
        titleMatchesAndBorrowed.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        titleMatchesAndBorrowed.borrowBook("Clean Code", "Robert C. Martin");
        assertNull(titleMatchesAndBorrowed.searchBookByTitle("Clean Code"));

        Library titleDoesNotMatchAndAvailable = new Library();
        titleDoesNotMatchAndAvailable.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));
        assertNull(titleDoesNotMatchAndAvailable.searchBookByTitle("Clean Code"));

        Library titleDoesNotMatchAndBorrowed = new Library();
        titleDoesNotMatchAndBorrowed.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));
        titleDoesNotMatchAndBorrowed.borrowBook("Effective Java", "Joshua Bloch");
        assertNull(titleDoesNotMatchAndBorrowed.searchBookByTitle("Clean Code"));
    }

    @Test
    void testSearchBookByTitle_found() {
        Library lib = new Library();
        lib.addBook(new Book("Harry Potter", "J.K. Rowling", "Fantasy"));

        List<Book> result = lib.searchBookByTitle("Harry Potter");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Harry Potter", result.get(0).getTitle());
    }

    @Test
    void testSearchBookByTitle_notFound() {
        Library lib = new Library();
        lib.addBook(new Book("Harry Potter", "J.K. Rowling", "Fantasy"));

        List<Book> result = lib.searchBookByTitle("Lord of the Rings");

        assertNull(result);
    }

    @Test
    void testSearchBookByTitle_emptyTitle() {
        Library lib = new Library();
        assertThrows(IllegalArgumentException.class, () -> lib.searchBookByTitle(""));
    }

    @Test
    void testBorrowBook_success() {
        Library lib = new Library();
        lib.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));

        assertDoesNotThrow(() -> lib.borrowBook("Clean Code", "Robert C. Martin"));
    }

    @Test
    void borrowBookEveryBranchTest() {
        Library successLibrary = new Library();
        successLibrary.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        assertDoesNotThrow(() -> successLibrary.borrowBook("Clean Code", "Robert C. Martin"));

        Library alreadyBorrowedLibrary = new Library();
        alreadyBorrowedLibrary.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));
        alreadyBorrowedLibrary.borrowBook("Effective Java", "Joshua Bloch");
        assertThrows(RuntimeException.class,
                () -> alreadyBorrowedLibrary.borrowBook("Effective Java", "Joshua Bloch"));

        Library notFoundLibrary = new Library();
        notFoundLibrary.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        assertThrows(RuntimeException.class,
                () -> notFoundLibrary.borrowBook("Unknown Book", "Unknown Author"));

        Library invalidInputLibrary = new Library();
        assertThrows(IllegalArgumentException.class,
                () -> invalidInputLibrary.borrowBook("", "Robert C. Martin"));
    }

    @Test
    void borrowBookMultipleConditionTest() {
        Library bothEmptyLibrary = new Library();
        assertThrows(IllegalArgumentException.class,
                () -> bothEmptyLibrary.borrowBook("", ""));

        Library titleEmptyLibrary = new Library();
        assertThrows(IllegalArgumentException.class,
                () -> titleEmptyLibrary.borrowBook("", "Robert C. Martin"));

        Library authorEmptyLibrary = new Library();
        assertThrows(IllegalArgumentException.class,
                () -> authorEmptyLibrary.borrowBook("Clean Code", ""));

        Library bothNotEmptyLibrary = new Library();
        bothNotEmptyLibrary.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        assertDoesNotThrow(() -> bothNotEmptyLibrary.borrowBook("Clean Code", "Robert C. Martin"));
    }

    @Test
    void testBorrowBook_alreadyBorrowed() {
        Library lib = new Library();
        lib.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        lib.borrowBook("Clean Code", "Robert C. Martin");

        assertThrows(RuntimeException.class, () -> lib.borrowBook("Clean Code", "Robert C. Martin"));
    }

    @Test
    void testBorrowBook_notFound() {
        Library lib = new Library();
        lib.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));

        assertThrows(RuntimeException.class, () -> lib.borrowBook("Unknown", "Someone"));
    }

    @Test
    void testReturnBook_success() {
        Library lib = new Library();
        lib.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        lib.borrowBook("The Hobbit", "J.R.R. Tolkien");

        assertDoesNotThrow(() -> lib.returnBook("The Hobbit"));
    }

    @Test
    void testReturnBook_notBorrowed() {
        Library lib = new Library();
        lib.addBook(new Book("1984", "George Orwell", "Dystopian"));

        assertThrows(RuntimeException.class, () -> lib.returnBook("1984"));
    }

    @Test
    void testReturnBook_notFound() {
        Library lib = new Library();
        assertThrows(RuntimeException.class, () -> lib.returnBook("Unknown"));
    }
}
