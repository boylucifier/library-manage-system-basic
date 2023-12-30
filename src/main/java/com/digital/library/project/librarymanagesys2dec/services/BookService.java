package com.digital.library.project.librarymanagesys2dec.services;

import com.digital.library.project.librarymanagesys2dec.exceptionsModel.AuthorNotFoundException;
import com.digital.library.project.librarymanagesys2dec.exceptionsModel.BookNotFoundException;
import com.digital.library.project.librarymanagesys2dec.models.Author;
import com.digital.library.project.librarymanagesys2dec.models.Book;
import com.digital.library.project.librarymanagesys2dec.repositery.BookRepos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookRepos bookRepos;
    @Autowired
    AuthorService authorService;
    public void addBook(Book book) {
        //first check if author is already present or not if it is then don't create duplicate one
        Author author = authorService.getOrCreateAuthorByEmail(book.getMyAuthor());
        book.setMyAuthor(author);
        bookRepos.save(book);
    }
    public Book getBookById(Integer id) throws BookNotFoundException{
        return bookRepos.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found by given id"));
    }

    public List<Book> getAllBook() {
        return bookRepos.findAll();
    }

    public List<Book> getAllBookByAuthId(Integer id) throws AuthorNotFoundException {
            return authorService.getAllBooks(id);
    }

}
