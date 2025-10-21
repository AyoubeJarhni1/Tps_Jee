package com.example.service;

import com.example.model.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface BookService {

    @WebMethod
    Book createBook(
	    @WebParam(name = "title") String title,
	    @WebParam(name = "author") String author,
	    @WebParam(name = "isbn") String isbn,
	    @WebParam(name = "year") int year
    );

    @WebMethod
    Book getBook(@WebParam(name = "id") Long id) throws Exception;

    @WebMethod
    List<Book> getAllBooks();

    @WebMethod
    Book updateBook(
	    @WebParam(name = "id") Long id,
	    @WebParam(name = "title") String title,
	    @WebParam(name = "author") String author,
	    @WebParam(name = "isbn") String isbn,
	    @WebParam(name = "year") int year
    ) throws Exception;

    @WebMethod
    boolean deleteBook(@WebParam(name = "id") Long id) throws Exception;
}
