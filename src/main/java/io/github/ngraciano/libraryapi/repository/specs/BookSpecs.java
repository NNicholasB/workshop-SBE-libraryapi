package io.github.ngraciano.libraryapi.repository.specs;

import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.model.GenderBook;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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

    public static Specification<Book> yearPublicationEqual(Integer yearPublication){
        return (root, query, cb) ->cb.equal(cb.function("to_char",String.class,root.get("datePublication"),cb.literal("YYYY")),yearPublication.toString());
    }

    public static Specification<Book> nameAuthorLike(String name){
        return (root, query, cb) ->{
            Join<Object, Object> joinAuthor= root.join("author", JoinType.LEFT);
              return cb.like(cb.upper(joinAuthor.get("name")),"%"+name.toUpperCase()+"%");
        };
    }
}
