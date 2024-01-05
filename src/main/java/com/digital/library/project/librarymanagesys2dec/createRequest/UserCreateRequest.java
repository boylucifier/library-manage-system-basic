package com.digital.library.project.librarymanagesys2dec.createRequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    @NotBlank
    @Column(unique = true)
    private String userName;
    @Size(min = 5,max = 16)
    @NotBlank
    private String password;
    
}
