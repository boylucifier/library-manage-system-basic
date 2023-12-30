package com.digital.library.project.librarymanagesys2dec.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;
    private int age;
    @Column(nullable = false,unique = true)
    private String email;
    private String studentName;
    @OneToMany(mappedBy="student")
    @JsonIgnoreProperties(value="student")
    private List<Book> books;
    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties(value = "student")
    private List<Transaction> transactions;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

}
