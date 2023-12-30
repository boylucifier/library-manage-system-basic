package com.digital.library.project.librarymanagesys2dec.createRequest;

import com.digital.library.project.librarymanagesys2dec.models.Student;
import com.digital.library.project.librarymanagesys2dec.repositery.StudentRepos;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

    public Student to(){
        return Student.builder().studentName(name)
                .age(age).email(email).build();

    }
}
