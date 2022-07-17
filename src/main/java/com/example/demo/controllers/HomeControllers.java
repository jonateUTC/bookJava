package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false") 
@RestController
@RequestMapping("/api")
public class HomeControllers {
    @Autowired
    BookRepository bookRepository;
    
    @GetMapping("/books")
    Page<Book> getBooks(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy, @RequestParam Optional<String> sorttype, @RequestParam Optional<Integer> size){
        String order = sorttype.get();
        if (order.equals("desc")){
            return bookRepository.findAll(
                    PageRequest.of(
                            page.orElse(0),
                            size.orElse(5),
                            Sort.Direction.DESC, sortBy.orElse("id")
                    )               
            );
        }else{
            return bookRepository.findAll(
                    PageRequest.of(
                            page.orElse(0),
                            size.orElse(5),
                            Sort.Direction.ASC, sortBy.orElse("id")
                    )               
            );
        }
    }
    /*@GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String name) {
        try {
                List<Book> books = new ArrayList<Book>();

                if (name == null)
                        bookRepository.findAll().forEach(books::add);
                else
                        bookRepository.findByNamebookContaining(name).forEach(books::add);

                if (books.isEmpty()) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @PostMapping("/bookcreate")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Date firstDate = book.getDatepublishbook();
        Date secondDate = new Date();
        long diff = secondDate.getYear()- firstDate.getYear();
        try {
            if (diff <= 10){
                Book _book = bookRepository
                                .save(new Book(book.getNamebook(), book.getDescriptionbook(),book.getAuthorbook(),book.getDatepublishbook(),book.getNumberbook(),book.getPricebook()));
                return new ResponseEntity<>(_book, HttpStatus.CREATED);
            }else{               
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/booksupdate/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
        Date firstDate = book.getDatepublishbook();
        Date secondDate = new Date();
        long diff = secondDate.getYear()- firstDate.getYear();

        if (bookData.isPresent()) {
            if (diff <= 10){
                Book _book = bookData.get();
                _book.setNamebook(book.getNamebook());
                _book.setDescriptionbook(book.getDescriptionbook());
                _book.setAuthorbook(book.getAuthorbook());
                _book.setDatepublishbook(book.getDatepublishbook());
                _book.setNumberbook(book.getNumberbook());
                _book.setPricebook(book.getPricebook());
                return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
            }else{                
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/booksdelete/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
