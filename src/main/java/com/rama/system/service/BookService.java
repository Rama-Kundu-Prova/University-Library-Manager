package com.rama.system.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rama.system.model.Book;
import com.rama.system.repository.BookRepository;


@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public void addBook(Book book)
	{
		bookRepo.save(book);
	}
	
	public List<Book> getAllBook()
	{
		return bookRepo.findAll();
		
	}
	public Book getBookById(int bookId)
	{
		Optional<Book> book= bookRepo.findById(bookId);
		if(book.isPresent())
		{
			return book.get();
		}
		return null;
		
	}
	public void deleteBook(int bookId)
	{
		bookRepo.deleteById(bookId);
	}
	public Page<Book> getBookByPaginate(int currentPage, int size) {
		Pageable p = PageRequest.of(currentPage, size);
		return bookRepo.findAll(p);
	}

}
