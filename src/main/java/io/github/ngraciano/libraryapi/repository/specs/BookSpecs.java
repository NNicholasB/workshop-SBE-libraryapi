package io.github.ngraciano.libraryapi.repository.specs;

import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.model.GenderBook;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book>isbnEqual(String isbn){
        return(root, query, cb)-> cb.equal(root.get("isbn"),isbn);
    }

    public static Specification<Book>titleLike(String title){
        return(root, query, cb)-> cb.equal(cb.upper(root.get("title")),"%"+title.toUpperCase()+"%");
    }
    public static Specification<Book>genderEqual(GenderBook gender){
        return(root, query, cb)-> cb.equal(root.get("gender"),gender);
    }
}
