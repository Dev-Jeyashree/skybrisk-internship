package com.skybrisk.bookstore.service;

import com.skybrisk.bookstore.exception.BookNotFoundException;
import com.skybrisk.bookstore.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book addBook(Book book) {
        book.setId(idCounter++);
        books.add(book);
        return book;
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPrice(updatedBook.getPrice());

        return existingBook;
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        books.remove(book);
    }
}
