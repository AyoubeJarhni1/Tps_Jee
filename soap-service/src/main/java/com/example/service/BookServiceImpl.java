package com.example.service;

import com.example.model.Book;
import jakarta.jws.WebService;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@WebService(endpointInterface = "com.example.service.BookService")
public class BookServiceImpl implements BookService {
	private final Map<Long, Book> books = new ConcurrentHashMap<>();
	private final AtomicLong counter = new AtomicLong(1);

	@Override
	public Book createBook(String title, String author, String isbn, int year) {
		validateInput(title, author, isbn, year);
		Long id = counter.getAndIncrement();
		Book book = new Book(id, title, author, isbn, year);
		books.put(id, book);
		return book;
	}

	@Override
	public Book getBook(Long id) throws Exception {
		if (id == null) throw new IllegalArgumentException("ID cannot be null");
		Book book = books.get(id);
		if (book == null) throw new Exception("Book with ID=" + id + " not found");
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		return new ArrayList<>(books.values());
	}

	@Override
	public Book updateBook(Long id, String title, String author, String isbn, int year) throws Exception {
		Book book = getBook(id);
		validateInput(title, author, isbn, year);
		book.setTitle(title);
		book.setAuthor(author);
		book.setIsbn(isbn);
		book.setYear(year);
		return book;
	}

	@Override
	public boolean deleteBook(Long id) throws Exception {
		if (id == null) throw new IllegalArgumentException("ID cannot be null");
		Book book = books.remove(id);
		if (book == null) throw new Exception("Book with ID=" + id + " not found");
		return true;
	}

	private void validateInput(String title, String author, String isbn, int year) {
		if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Title cannot be empty");
		if (author == null || author.trim().isEmpty()) throw new IllegalArgumentException("Author cannot be empty");
		if (isbn == null || isbn.trim().isEmpty()) throw new IllegalArgumentException("ISBN cannot be empty");
		if (year < 1000 || year > 9999) throw new IllegalArgumentException("Year must be valid");
	}
}
