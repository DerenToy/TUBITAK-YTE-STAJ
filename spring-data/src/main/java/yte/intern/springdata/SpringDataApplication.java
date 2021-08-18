package yte.intern.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import yte.intern.springdata.entities.Book;
import yte.intern.springdata.entities.User;
import yte.intern.springdata.repositories.BookRepository;
import yte.intern.springdata.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringDataApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringDataApplication.class, args);

		//UserRepository userRepository = context.getBean(UserRepository.class);
		//userRepository.save(new User("hebele"));
		//System.out.println(userRepository.findByName("hebele"));
		BookRepository bookRepository = context.getBean(BookRepository.class);

		populateDatabase(context);
		querySolution(bookRepository);
/* DERİVED QUERY
		Optional<Book> dddOptional = bookRepository.findByTitle("Domain Driven Design");
		//System.out.println(dddOptional.get());
		System.out.println(bookRepository.findByAgeGreaterThanEqual(15L, Sort.by("age").ascending()));
		//System.out.println(bookRepository.findByPublishDateAfter(LocalDate.parse("2000-01-01"), PageRequest.of(1,5)));
		//System.out.println(bookRepository.findByTitleContains("Clean"));
		//System.out.println(bookRepository.findByAuthorAndAgeGreaterThan("Robert C. Martin", 10L));
		System.out.println(bookRepository.existsByAuthor("Robert C. Martin"));



 */


	}
	private static void populateDatabase(ApplicationContext context) {
		BookRepository bookRepository = context.getBean(BookRepository.class);
		List<Book> exampleBooks = new ArrayList<>();
		exampleBooks.add(new Book(null, "Clean Code", "Robert C. Martin", 11L, LocalDate.parse("2008-07-11")));
		exampleBooks.add(new Book(null, "Clean Agile", "Robert C. Martin", 0L, LocalDate.parse("2019-09-12")));
		exampleBooks.add(new Book(null, "Agile Software Development", "Robert C. Martin", 17L, LocalDate.parse("2002-10-25")));
		exampleBooks.add(new Book(null, "Code Complete 2", "Steve McConnell", 26L, LocalDate.parse("1993-05-30")));
		exampleBooks.add(new Book(null, "Essential Scrum", "Kenneth S. Rubin", 7L, LocalDate.parse("2012-07-20")));
		exampleBooks.add(new Book(null, "Design Patterns", "Gang of Four", 25L, LocalDate.parse("1994-10-01")));
		exampleBooks.add(new Book(null, "Domain Driven Design", "Eric Evans", 16L, LocalDate.parse("2003-08-30")));
		exampleBooks.add(new Book(null, "Test Driven Development", "Kent Beck", 17L, LocalDate.parse("2002-11-18")));
		exampleBooks.add(new Book(null, "Refactoring", "Kent Beck", 7L, LocalDate.parse("2012-03-09")));
		exampleBooks.add(new Book(null, "Extreme Programming Explained", "Kent Beck", 15L, LocalDate.parse("2004-11-26")));

		bookRepository.saveAll(exampleBooks);
	}
	private static void querySolution(BookRepository bookRepository) {
		List<Book> domainDrivenDesignBook = bookRepository.findByTitleQuery("Domain Driven Design");
		System.out.println(domainDrivenDesignBook);

		List<Book> ageBooks = bookRepository.findByAgeGreaterThanEqualQuery(15L, Sort.by("age").ascending());
		System.out.println(ageBooks);

		List<Book> booksByPublishDate = bookRepository.findByPublishDateAfterQuery(
				LocalDate.parse("2000-01-01"), PageRequest.of(1, 5));
		System.out.println(booksByPublishDate);

		List<Book> cleanBooks = bookRepository.findByTitleContainsQuery("Clean");
		System.out.println(cleanBooks);

		List<Book> robertMartinBooks = bookRepository.findByAuthorAndAgeGreaterThanQuery("Robert C. Martin", 10L);
		System.out.println(robertMartinBooks);

		long kentBeekBookCount = bookRepository.countByAuthorQuery("Kent Beck");
		System.out.println(kentBeekBookCount);
	}
}
