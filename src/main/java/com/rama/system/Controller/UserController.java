package com.rama.system.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rama.system.model.Book;
import com.rama.system.model.User;
import com.rama.system.repository.BookRepository;
import com.rama.system.repository.UserRepository;
import com.rama.system.service.BookService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BookService bookService;
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String email = p.getName();
		User user = userRepo.findByUserEmail(email);
		m.addAttribute("user", user);

	}
	
	@GetMapping("/home")
	public String home() {
		return "user/home";
	}
	@GetMapping("/changePassword")
	public String loadChangePassword()
	{
		return "user/change_password";
	}
	
	@GetMapping("/updateProfile")
	public String loadUpdateProfile()
	{
		return "user/update_profile";
	}
	@GetMapping("/profile")
	public String loadProfile()
	{
		return "user/profile";
	}
	
	@GetMapping("/registerBook")
	public String registerBook() 
	{
		return "user/register_book";
	}
	
	@PostMapping("/addBook")
	public String addBook(@ModelAttribute Book book )
	{
		bookService.addBook(book);
		return "redirect:/user/home";
	}
	
	@GetMapping("/edit/{bookId}")
	public String edit(@PathVariable int bookId, Model m) {
		Book book = bookService.getBookById(bookId);
		m.addAttribute("book", book);
		return "user/edit";
	}

	@PostMapping("/update")
	public String updateBook(@ModelAttribute Book book, Model m) {
		bookService.addBook(book);
		return findPaginated(0, m);
	}

	@GetMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable int bookId, Model m) {

		bookService.deleteBook(bookId);
		return findPaginated(0, m);
	}
	@GetMapping("/showBooklist")
	public String showBooklist(Model m) {
		return findPaginated(0, m);
	}
	
	@GetMapping("/page/{pageno}")
	public String findPaginated(@PathVariable int pageno, Model m) {

		Page<Book> book = bookService.getBookByPaginate(pageno, 2);
		m.addAttribute("book", book);
		m.addAttribute("currentPage", pageno);
		m.addAttribute("totalPages", book.getTotalPages());
		m.addAttribute("totalItem", book.getTotalElements());
		return "user/booklist";
	}

	

}


