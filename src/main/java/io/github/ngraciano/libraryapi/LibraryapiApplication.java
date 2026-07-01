package io.github.ngraciano.libraryapi;

import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.repository.AuthorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryapiApplication {

	public static void main(String[] args) {
		var context=SpringApplication.run(LibraryapiApplication.class, args);
		AuthorRepository repository=context.getBean(AuthorRepository.class);

		exUpdateRepository(repository);
	}


	public static void exUpdateRepository(AuthorRepository authorRepository){
		Author author= new Author();
		author.setName("Joao");
		author.setNationality("Brasileiro");
		author.setDateBirth(LocalDate.of(1950,1,31));

		authorRepository.save(author);

	}




}
