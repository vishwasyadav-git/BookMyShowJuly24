package com.scaler.bookmyshowjuly24.services;

import com.scaler.bookmyshowjuly24.controllers.UserController;
import com.scaler.bookmyshowjuly24.models.User;
import com.scaler.bookmyshowjuly24.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public User signUp(String name, String email, String password){
        Optional<User> optionalUser=userRepository.findByEmail(email);
        User savedUser =null;
        if(optionalUser.isPresent()){
            //Move to log in.
        }else{
            //create a user object and save to db
            User user=new User();
            user.setEmail(email);
            user.setName(name);
            //Bcrypt password encoder
            BCryptPasswordEncoder ps=new BCryptPasswordEncoder();
            user.setPassword(ps.encode(password));
            savedUser= userRepository.save(user);

        }
    return  savedUser;
    }
}
