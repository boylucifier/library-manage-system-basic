package com.digital.library.project.librarymanagesys2dec.security;

import com.digital.library.project.librarymanagesys2dec.createRequest.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepos userRepos;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Value("${user.authority.admin}")
    String admin_authority;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepos.findByUserName(username);
        if(null==user)
            throw new UsernameNotFoundException("user not found with mentioned username");
        return user;
    }
    public void saveUser(UserCreateRequest user){
        User newUser = User.builder().userName(user.getUserName()).password(passwordEncoder.encode(user.getPassword()))
                        .authorities(admin_authority).build();
        userRepos.save(newUser);
    }

    public void saveUser(User user){
        userRepos.save(user);
    }

    public List<User> getAllUsers() {
        return userRepos.findAll();
    }

    public User getUserById(Integer id) throws UsernameNotFoundException{
        return userRepos.findById(id).orElseThrow(()->new UsernameNotFoundException("User is not present by this id"));
    }
}
