package io.github.ngraciano.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "isbn",length = 20,nullable = false)
    private String isbn;

    @Column(name = "title",length = 150,nullable = false)
    private String title;

    @Column(name = "date_publication")
    private LocalDate dataPublication;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender",length = 30,nullable = false)
    private GenderBook gender;

    @Column(name = "price",precision = 18,scale = 2, nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name="id_author")
    private Author author;
}
