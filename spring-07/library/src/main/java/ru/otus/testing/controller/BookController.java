package ru.otus.testing.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

@Controller
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }


  @GetMapping({ "/by_id_book/{id}" })
  public BookDTO bookById(@PathVariable("id") long id) {
      return bookService.findById(id);
  }

  @GetMapping({ "/all_book" })
  public String allBooks(Model model) {
    List<Book> books = bookService.findAll();
    model.addAttribute("books", books);

    return "booklist";
  }

  @PostMapping({ "/save_book" })
  public String saveBook(Book book, Model model) {
    Book saved = bookService.save(book.getName(), book.getYear(), book.getAuthor(), book.getGenre());
    model.addAttribute(saved);

    return "redirect:/book";
  }


  @DeleteMapping({ "/del_book" })
  public String deleteBook(@RequestParam("id") long id) {
    bookService.delete(id);

    return "redirect:/book";
  }
}
