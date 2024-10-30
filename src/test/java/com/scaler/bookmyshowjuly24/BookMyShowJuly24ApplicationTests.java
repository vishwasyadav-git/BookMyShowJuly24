package com.scaler.bookmyshowjuly24;

import com.scaler.bookmyshowjuly24.controllers.UserController;
import com.scaler.bookmyshowjuly24.dtos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookMyShowJuly24ApplicationTests {

    @Test
    void contextLoads() {
    }
@Autowired
    private  UserController userController;
    @Test
    public  void testSignUpFunctionality(){
        SignUpRequestDto requestDto=new SignUpRequestDto();
        requestDto.setName("Abhi");
        requestDto.setEmail("Abhi@scaler.com");
        requestDto.setPassword("c234d");

        SignUpResponseDto responseDto= userController.signUp(requestDto);
        System.out.println(responseDto.getUserId());
    }

    @Test
    public void testLoginFunctionality(){
        LoginRequestDto loginRequestDto=new LoginRequestDto();
        loginRequestDto.setEmail("checkid@scaler.com");
        loginRequestDto.setPassword("c234d");

        LoginResponseDto loginResponseDto= userController.login(loginRequestDto);
        if(loginResponseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)){
            System.out.println("Login Successful");
        }else{
            System.out.println("Login UnSuccessful");
        }

    }

}
