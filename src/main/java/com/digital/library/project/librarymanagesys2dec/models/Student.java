package com.digital.library.project.librarymanagesys2dec.models;

import com.digital.library.project.librarymanagesys2dec.security.User;
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
    @OneToOne
    @JsonIgnoreProperties(value="student")
    @JoinColumn
    private User user;

    @Column(unique = true,nullable = false)
    private String userName;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

}
