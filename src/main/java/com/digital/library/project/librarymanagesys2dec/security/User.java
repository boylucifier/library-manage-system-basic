package com.digital.library.project.librarymanagesys2dec.security;

import com.digital.library.project.librarymanagesys2dec.models.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String userName;
    private String password;
    private String authorities;
    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties(value="user")
    private Student student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] arr = this.authorities.split(",");
        Set<SimpleGrantedAuthority> authoritySet = Arrays.stream(arr)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return authoritySet;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
