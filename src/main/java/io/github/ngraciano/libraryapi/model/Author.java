package io.github.ngraciano.libraryapi.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="author")
@Data
@ToString(exclude = {"books"})
@EntityListeners(AuditingEntityListener.class)
public class Author {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100,nullable = false)
    private String name;

    @Column(name="date_birth",nullable = false)
    private LocalDate dateBirth;

    @Column(name = "nationality",length = 50)
    private String nationality;


 @OneToMany(mappedBy = "author")
 private List<Book> books;

    @CreatedDate
    @Column(name="date_registration")
    private LocalDateTime dateRegistration;

    @LastModifiedDate
    @Column(name="date_update")
    private LocalDateTime dateUpdate;

    @Column(name="id_user")
    private UUID idUser;
}
