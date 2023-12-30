package com.digital.library.project.librarymanagesys2dec.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties(value = "books")
    private Author myAuthor;
    @Enumerated(value = EnumType.STRING)//Ordinal is stored as number
    private Genre genre;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties(value="books")
    private Student student;
    @OneToMany(mappedBy = "book")
    private List<Transaction> transaction;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;
    @Positive
    private int price;



}