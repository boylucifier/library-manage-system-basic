package com.digital.library.project.librarymanagesys2dec.controllers;

import com.digital.library.project.librarymanagesys2dec.createRequest.BookCreateRequest;
import com.digital.library.project.librarymanagesys2dec.exceptionsModel.AuthorNotFoundException;
import com.digital.library.project.librarymanagesys2dec.exceptionsModel.BookNotFoundException;
import com.digital.library.project.librarymanagesys2dec.models.Book;
import com.digital.library.project.librarymanagesys2dec.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/createBook")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        bookService.addBook(bookCreateRequest.to());
    }
    @RequestMapping(value="/getBookById/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable("id") Integer id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }

    @GetMapping(value="/getAllBook")
    public List<Book> getAllBooks(){
        return bookService.getAllBook();
    }

    @GetMapping(value="/getAllBooksByAuthorId")
    public ResponseEntity<List<Book>> getAllBooksByAuthorId(@RequestParam("authorId") Integer id) throws AuthorNotFoundException {
        return new ResponseEntity<>(bookService.getAllBookByAuthId(id), HttpStatus.ACCEPTED);
    }

}
