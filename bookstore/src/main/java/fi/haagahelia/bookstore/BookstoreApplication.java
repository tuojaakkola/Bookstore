package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository){return (args) -> {
	Book book1 = new Book("Java Programming", "John Doe", "1234567890", 2020, 29.99);
	Book book2 = new Book("Spring Basics", "Jane Smith", "0987654321", 2019, 24.99);
	Book book3 = new Book("Clean Code", "Robert Martin", "1122334455", 2008, 39.99);
	Book book4 = new Book("Design Patterns", "Erich Gamma", "5566778899", 1994, 34.99);
	Book book5 = new Book("Algorithms", "Thomas Cormen", "9988776655", 2001, 49.99);

	repository.save(book1);
	repository.save(book2);
	repository.save(book3);
	repository.save(book4);
	repository.save(book5);
	};
	}
}
