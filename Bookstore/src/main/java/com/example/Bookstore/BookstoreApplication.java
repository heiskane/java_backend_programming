package com.example.Bookstore;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("Commanline runner test");
			categoryRepository.save(new Category("Thriller"));
			categoryRepository.save(new Category("Crime"));

			bookRepository.save(new Book(
					"test book",
					"test author",
					1996,
					"isbn123",
					10F,
					categoryRepository.findByName("Thriller").get(0)));
			bookRepository.save(new Book(
					"test book2",
					"test author2",
					1999,
					"isbn123",
					20F,
					categoryRepository.findByName("Crime").get(0)));


			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
