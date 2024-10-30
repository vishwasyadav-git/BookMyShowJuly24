package com.scaler.bookmyshowjuly24.services;

import com.scaler.bookmyshowjuly24.controllers.UserController;
import com.scaler.bookmyshowjuly24.exception.UserNotFoundException;
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
        /*
        1. Check if the user already exists with the given email id.
        2. If yes, ask the user to login.
        2. If not, create a new user object with the given details.
        3. save it to DB.
         */
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
    public User login(String email,String password) throws UserNotFoundException {
        Optional<User> optionalUser=userRepository.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email "+email +" not found");
        }
        User user= optionalUser.get();
        //compare the password
        //Bcrypt password encoder
        BCryptPasswordEncoder ps=new BCryptPasswordEncoder();
//        if(ps.encode(password).equals(user.getPassword())){
//           will not work
//        }
        if(ps.matches(password, user.getPassword())){
            //Login successful
            return user;
        }
        throw  new RuntimeException("password is Incorrect");
    }
}
