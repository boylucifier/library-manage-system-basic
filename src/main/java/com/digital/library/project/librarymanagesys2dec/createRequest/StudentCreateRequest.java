package com.digital.library.project.librarymanagesys2dec.createRequest;

import com.digital.library.project.librarymanagesys2dec.models.Student;
import com.digital.library.project.librarymanagesys2dec.repositery.StudentRepos;
import com.digital.library.project.librarymanagesys2dec.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentCreateRequest {
    @NotBlank
    private String name;
    @Positive
    private int age;
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 14,min = 5)
    private String password;

    @NotBlank
    private String userName;

    public Student to(){
        return Student.builder().studentName(name)
                .age(age).email(email).userName(userName)
                .user(User.builder().userName(userName).password(password).build()).build();

    }
}
