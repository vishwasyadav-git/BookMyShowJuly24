package com.scaler.bookmyshowjuly24;

import com.scaler.bookmyshowjuly24.controllers.UserController;
import com.scaler.bookmyshowjuly24.dtos.SignUpRequestDto;
import com.scaler.bookmyshowjuly24.dtos.SignUpResponseDto;
import com.scaler.bookmyshowjuly24.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowJuly24Application implements CommandLineRunner {
@Autowired
    private   UserController userController;
//    public BookMyShowJuly24Application(UserController userController){
//        this.userController=userController;
//    }
    public static void main(String[] args) {
        SpringApplication.run(BookMyShowJuly24Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto requestDto=new SignUpRequestDto();
        requestDto.setName("TemporalType.DATE");
        requestDto.setEmail("jpaauditing@scaler.com");
        requestDto.setPassword("arya");

        SignUpResponseDto responseDto= userController.signUp(requestDto);
    }
}
