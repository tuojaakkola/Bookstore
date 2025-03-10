package fi.haagahelia.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.Category;

@DataJpaTest

public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void createNewBook() {

        Category category = new Category("Test Category");
        categoryRepository.save(category);
        Book book = new Book("Test Title", "Test Author", "293123", 2020, 29.99, category);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBook() {

        List<Book> books = bookRepository.findByTitle("Test Title");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> deletedBook = bookRepository.findByTitle("Test Title");
        assertThat(deletedBook).isEmpty();
    }

    @Test
    public void findBookByTitle() {

        Category category = new Category("Test Category");
        categoryRepository.save(category);
        Book book = new Book("Test Title", "Test Author", "1234567890", 2020, 29.99, category);
        bookRepository.save(book);
        Book foundBook = bookRepository.findByTitle("Test Title").get(0);

        assertThat(foundBook).isEqualTo(book);
    }
}
