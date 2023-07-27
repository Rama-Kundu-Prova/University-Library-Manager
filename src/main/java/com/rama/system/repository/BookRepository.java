package com.rama.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rama.system.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	

}
