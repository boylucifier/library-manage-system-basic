package com.digital.library.project.librarymanagesys2dec.services;

import com.digital.library.project.librarymanagesys2dec.exceptionsModel.AuthorNotFoundException;
import com.digital.library.project.librarymanagesys2dec.models.Author;
import com.digital.library.project.librarymanagesys2dec.models.Book;
import com.digital.library.project.librarymanagesys2dec.repositery.AuthorRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepos authorRepos;

    public Author getOrCreateAuthorByEmail(Author author){
        Author authorFromDb = authorRepos.getAuthorByEmail(author.getEmail());
        if(null == authorFromDb){
            authorFromDb = authorRepos.save(author);
        }
        return authorFromDb;
    }

    public List<Book> getAllBooks(Integer id) throws AuthorNotFoundException {
        Author author = authorRepos.findById(id).orElse(null);
        if(null == author)
            throw new AuthorNotFoundException("No Author found by given id");
        else
            return author.getBooks();
    }
}
