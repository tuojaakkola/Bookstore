package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository cRepository){return (args) -> {

			Category category1 = new Category("Coding");
			Category category2 = new Category("Design");
			Category category3 = new Category("Animals");
			
			cRepository.save(category1);
			cRepository.save(category2);
			cRepository.save(category3);	

			Book book1 = new Book("Java Programming", "John Doe", "1234567890", 2020, 29.99, category1);
			Book book2 = new Book("Spring Basics", "Jane Smith", "0987654321", 2019, 24.99, category1);
			Book book3 = new Book("Clean Code", "Robert Martin", "1122334455", 2008, 39.99, category1);
			Book book4 = new Book("Design Patterns", "Erich Gamma", "5566778899", 1994, 34.99, category2);
			Book book5 = new Book("Dog's life", "Thomas Cormen", "9988776655", 2001, 49.99, category3);

			repository.save(book1);
			repository.save(book2);
			repository.save(book3);
			repository.save(book4);
			repository.save(book5);
	};
	}
}
