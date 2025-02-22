package fi.haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.Book;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class BookstoreRestController {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository crepository;

    @GetMapping("/books")
        public List<Book> bookListRest() {
            return (List<Book>)repository.findAll();
        }

    @GetMapping("/books/{id}")
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
        return repository.findById(bookId).get();
    }
    
}
