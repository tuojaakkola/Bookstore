package fi.haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.Book;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class BookstoreRestController {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository crepository;

    // RESTful service to get all books
    @GetMapping("/books")
        public List<Book> bookListRest() {
            return (List<Book>)repository.findAll();
        }

    // RESTful service to get a book by id
    @GetMapping("/books/{id}")
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
        return repository.findById(bookId).get();
    }
    
}
